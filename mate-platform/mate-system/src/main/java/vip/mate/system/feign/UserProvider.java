package vip.mate.system.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.system.entity.User;
import vip.mate.system.service.IUserService;

@Slf4j
@RestController
@AllArgsConstructor
//@Service(interfaceClass = IUserApi.class)
public class UserProvider implements IUserProvider {

    private final IUserService userService;

    @Override
    @GetMapping("/provider/user-info-by-id")
    public Result<User> userInfoById(Long userId) {
        User user = userService.getById(userId);
        return Result.data(user);
    }

    @Override
    @GetMapping("/provider/user-info")
    public Result<User> loadUserByUserName(String userName) {
        User user = userService.getOne(new QueryWrapper<User>().lambda().in(User::getAccount, userName));
        log.info("feign调用：user:{}", user);
        return Result.data(user);
    }
}