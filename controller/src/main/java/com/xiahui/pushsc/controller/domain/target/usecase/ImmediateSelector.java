package com.xiahui.pushsc.controller.domain.target.usecase;

import com.alibaba.fastjson.JSONObject;
import com.xiahui.pushsc.controller.domain.protocol.param.TargetParam;
import com.xiahui.pushsc.controller.domain.target.TargetSelector;
import lombok.Value;
import org.springframework.util.Assert;

import javax.validation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * 目标直接指定实现
 *
 * @author xiaohui
 * create on 2020-04-06
 */
public class ImmediateSelector implements TargetSelector {

    private Param param;

    public ImmediateSelector(TargetParam targetParam) {
        String useCase = targetParam.getUseCase();
        Assert.isTrue(getUseCase().equals(useCase), "error use case");

        // 这里最好不要偷懒用fastJson，底层是反射，影响性能
        JSONObject params = targetParam.getParams();
        param = new Param(params.getJSONArray("items").toJavaList(String.class), params.getString("accountType"));
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Param>> validate = validator.validate(param);
        for (ConstraintViolation<Param> violation : validate) {
            throw new IllegalArgumentException(violation.getMessage());
        }
    }

    @Override
    public String getUseCase() {
        return "immediate";
    }

    @Override
    public long getTargetCount() {
        return param.getItems().size();
    }

    @Override
    public JSONObject getTargetPage() {

        JSONObject result = new JSONObject();
        result.put("users", param.getItems());

        return result;
    }

    @Value
    static class Param {

        @NotEmpty(message = "推送目标数量不可为空")
        @Size(max = 1000, message = "推送目标不可超过1000个")
        List<String> items;
        @NotBlank(message = "账户类型不可为空")
        String accountType;

        @Valid
        public Param(@NotEmpty(message = "推送目标数量不可为空") @Size(max = 1000, message = "推送目标不可超过1000个") List<String> items, @NotBlank(message = "账户类型不可为空") String accountType) {
            this.items = items;
            this.accountType = accountType;
        }
    }
}
