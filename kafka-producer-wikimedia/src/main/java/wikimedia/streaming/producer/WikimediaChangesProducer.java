package wikimedia.streaming.producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {
    public static final Logger logger= LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String,String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage() throws InterruptedException {
        String topic="wikimedia_recentChanges";
        String url="https://stream.wikimedia.org/V2/stream/recentchange";
        EventHandler WikimediaChangesHandler=new WikimediaChangesHandler(kafkaTemplate,topic);
        EventSource.Builder builder=new EventSource.Builder(WikimediaChangesHandler,URI.create(url));
        EventSource eventSource= builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
