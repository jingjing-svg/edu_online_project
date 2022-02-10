import com.isjingjing.vod.service.VodService;
import com.isjingjing.vod.service.impl.VodServiceImpl;
import org.aspectj.weaver.ast.Var;
import org.junit.Test;

/**
 * @authors:静静
 * @description:null
 */
public class delBatchTest {

    VodService service = new VodServiceImpl();

    @Test
    public void test(){
        service.delBatchVideos("11d6fb6a459140d394db3db18c15d1c9,f79c6bc6c4194cb9871cb2d7d509d305");
    }
}
