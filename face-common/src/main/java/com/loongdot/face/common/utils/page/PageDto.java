package com.loongdot.face.common.utils.page;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.io.Serial;
import java.io.Serializable;

// @formatter:off
/**
 * @author ：coderafeng
 * @email  ：
 *     - Gmail：<a href="k1994583917@gmail.com">Gmail Email</a>
 *     - QQ：<a href="1994583917@qq.com">QQ Email</a>
 * @home   ：
 *     - <a href="http://loongdot.com/community">龙点科技有限责任公司</a>
 *     - <a href="https://github.com/coderafeng">GitHub</a>
 *     - <a href="https://gitee.com/coderafeng">GitHub</a>
 *     - <a href="https://space.bilibili.com/481342296">哔哩哔哩</a>
 * @company：<a href="http://loongdot.com">龙点科技有限责任公司</a>
 * @date   ：2025-03-15 17:09
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <br>
 * @description ：
 * <br>
 */
// @formatter:on
public class PageDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -6963871310150764081L;

    @Schema(description = "页码")
    @Min(value = 1, message = "页码从1开始")
    private long pageNumber = 1;
    @Schema(description = "页大小")
    @Min(value = 1, message = "页大小最小1")
    @Max(value = 2000, message = "页大小最大2000")
    private long pageSize = 5;


    @Min(value = 1, message = "页码从1开始")
    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(@Min(value = 1, message = "页码从1开始") long pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Min(value = 1, message = "页大小从1开始")
    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(@Min(value = 1, message = "页大小从1开始") long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }

    public PageDto() {
    }

    public PageDto(long pageNumber, long pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    /**
     * 装换 PageVo<T>类
     */
    public <T> PageVo<T> toPageVo(Class<T> cl) {
        return new PageVo<T>(pageNumber, pageSize);
    }


}
