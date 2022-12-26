# aws-lambdas
AWS Lambdas working demo

A project is divided into a set of sub-projects and implements a business-process:

Step function "CallSNSPublisherLambda" invokes lambda function "SNSPublisher".
"SNSPublisher" publishes a message into topic "topicForLambda".
Message triggers a lambda function "SNS2SQSTransmitter".
"SNS2SQSTransmitter" reads message from topic and sends it to SQS queue "QueueForLambdaDemo".