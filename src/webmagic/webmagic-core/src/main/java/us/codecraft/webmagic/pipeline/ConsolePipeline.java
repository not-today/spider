package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Write results in console.<br>
 * Usually used in test.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class ConsolePipeline implements Pipeline {


	protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void process(ResultItems resultItems, Task task) {
    logger.info("get page: " + resultItems.getRequest().getUrl());

    }
}
