#!/bin/bash
set -e

# Variables
BUCKET_NAME="cf-templates-localstack"
STACK_NAME="patient-management"
ENDPOINT_URL="http://localhost:4566"
TEMPLATE_FILE="./cdk.out/localstack.template.json"
TEMPLATE_KEY="localstack.template.json"
TEMPLATE_URL="https://$BUCKET_NAME.s3.amazonaws.com/$TEMPLATE_KEY"

echo "Creating S3 bucket if it doesn't exist..."
aws --endpoint-url=$ENDPOINT_URL s3api create-bucket \
    --bucket $BUCKET_NAME || true

echo "Uploading template to S3..."
aws --endpoint-url=$ENDPOINT_URL s3 cp "$TEMPLATE_FILE" "s3://$BUCKET_NAME/$TEMPLATE_KEY"

# Check if stack exists
echo "Checking if stack exists..."
set +e
STACK_EXISTS=$(aws --endpoint-url=$ENDPOINT_URL cloudformation describe-stacks \
    --stack-name $STACK_NAME 2>&1)
set -e

if [[ $STACK_EXISTS == *"does not exist"* ]]; then
    echo "Stack does not exist, creating new one..."
    aws --endpoint-url=$ENDPOINT_URL cloudformation create-stack \
        --stack-name $STACK_NAME \
        --template-url "$TEMPLATE_URL" \
        --capabilities CAPABILITY_NAMED_IAM
else
    echo "Stack exists, updating..."
    aws --endpoint-url=$ENDPOINT_URL cloudformation update-stack \
        --stack-name $STACK_NAME \
        --template-url "$TEMPLATE_URL" \
        --capabilities CAPABILITY_NAMED_IAM
fi

echo "Waiting for stack to finish..."
aws --endpoint-url=$ENDPOINT_URL cloudformation wait stack-create-complete \
    --stack-name $STACK_NAME || \
aws --endpoint-url=$ENDPOINT_URL cloudformation wait stack-update-complete \
    --stack-name $STACK_NAME

echo "Fetching Load Balancer DNS..."
aws --endpoint-url=$ENDPOINT_URL elbv2 describe-load-balancers \
    --query "LoadBalancers[0].DNSName" --output text
