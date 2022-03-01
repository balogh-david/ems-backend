package hu.bdavid.ems.app.rest.header;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

@Getter
@Setter
public class BaseHeader implements IBaseHeader {

    private String token;

    private String userName;

    public static BaseHeader createBaseHeader(HttpServletRequest httpServletRequest) {
        Assert.notNull(httpServletRequest, "httpServletRequest is NULL!");
        BaseHeader baseHeader = new BaseHeader();
        baseHeader.setToken(httpServletRequest.getHeader(IBaseHeader.HEADER_TOKEN));
        baseHeader.setUserName(httpServletRequest.getHeader(IBaseHeader.HEADER_USER_NAME));
        return baseHeader;
    }
}
