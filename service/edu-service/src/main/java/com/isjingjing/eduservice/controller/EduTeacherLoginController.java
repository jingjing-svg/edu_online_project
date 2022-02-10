package com.isjingjing.eduservice.controller;

import com.isjingjing.utils.result.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @authors:静静
 * @description:null
 */
@RestController
@CrossOrigin
@RequestMapping("eduService/user/")
public class EduTeacherLoginController {
    
    @PostMapping("login")
    public Result login(){

        return Result.success().data("token","admin");

    }

    @GetMapping("info")
    public Result getInfo(){return Result.success().data("roles","roles").data("name", "admin").data("avatar", "https://www.baidu.com/link?url=EJ8oDU9cLCLVAOGvDt_ubdhdytvOy35reQIGIcPSM0tWV37ohlg0B0_XbRRJ2rOKop5Fl4YS5SsCBbgZ0CAWTvJsdqCBclY5ZMdYY3FATWdygpwSsS-yz7HtiUgh-JSLQn8mtgRShOBqOFlkp7TmNriIl_4JWhPsoJidV8tG5Y9HqUnzNC5OM1pg6cBlJGCf8sY7Csga3db930ztYTOo_UE213PzfzreRAvvM-_RQNsrH_rN-_sKX_yUsdIoeFBwdAOlC3lWETWBd0vkT7WrguiSyfxHVTQhGK_UByxeyQ9n-QyDMP6RFn-JDbnSNjZ88jlIynRmBGlgn901EZNXiAHB_ixHuPvPl9VlNLb5UQXCmSOk_UFQiVlGbFrIJw4y_Ao-AnHhB-S73h46TSumQbDkjhyzTwJJHwmhh66urPCJHphPpogqA44EtgIYT1VKcI-OJe1W--FbIBwNDMc51I8dpCAmo5u6ldaeRcKKpY7DUZ9LyeYLwPstPmedjS3Wd96qVHH2HFMbZ2mez_zajbiSj5o_rFi5AE2p7GPTnKwakRrY4ZmzKM-tv6hp5umPfnKIFbalSocStxgiy0mUp_E3x3Hjkdz_gnsNij67tbXF42yVK4w4TybOwsHHjmyhQODFlV9gu6glplaFCnte3Oy02h0hxWZQk9gAlyAiEOpkSnueTOn8xDH8vT8_Nlez&wd=&eqid=a75f5718000720d30000000361778a5c");}
    
}
