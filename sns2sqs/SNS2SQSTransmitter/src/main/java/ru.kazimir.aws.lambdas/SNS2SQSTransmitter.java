package ru.kazimir.aws.lambdas;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Handler for requests to Lambda function.
 */
@Slf4j
public class SNS2SQSTransmitter implements RequestHandler<SNSEvent, SendMessageResult> {

    final String QUEUE_URL = "https://sqs.eu-north-1.amazonaws.com/372401390796/QueueForLambdaDemo";

    AmazonSQS sqsClient = (AmazonSQS) AmazonSQSClient.builder()
            .withRegion(Regions.EU_NORTH_1)
            .build();

    public SendMessageResult handleRequest(final SNSEvent snsEvent, final Context context) {
        log.debug("Got SNS event: " + snsEvent.toString());
        SendMessageRequest request = new SendMessageRequest(QUEUE_URL, snsEvent.getRecords().get(0).getSNS().getMessage());
        SendMessageResult result = sqsClient.sendMessage(request);
        log.debug("Published SQS event with result:" + result.toString());
        return result;
    }
}
