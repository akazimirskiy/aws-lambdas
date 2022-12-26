package ru.kazimir.aws.lambdas;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Handler for requests to Lambda function.
 */
@Slf4j
public class SNSPublisher implements RequestHandler<Object, PublishResult> {

    final String TOPIC_ARN = "arn:aws:sns:eu-north-1:372401390796:topicForLambda";
    AmazonSNS snsClient = (AmazonSNS) AmazonSNSClient.builder()
            .withRegion(Regions.EU_NORTH_1)
            .build();
    public PublishResult publishMessage() {
        PublishRequest request = new PublishRequest(TOPIC_ARN, "Sent from SNSPublisherLambda", "Message subject");
        PublishResult result = snsClient.publish(request);
        log.debug("Published SNS event with result: " + result.toString());
        return result;
    }

    @Override
    public PublishResult handleRequest(Object o, Context context) {
        return publishMessage();
    }
}