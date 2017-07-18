package china.test.eventbusbody;

import com.ztsc.house.eventbusbody.ZTAnyEventType;

/**
 * Created by Administrator on 2017/5/18.
 */

public class UploadHeartbeatEvent extends ZTAnyEventType {
        public UploadHeartbeatEvent() {
        }

        @Override
        public Object parseResult() {
            return null;
        }
}
