package com.loongdot.face.common.utils.page;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @date   ：2025-04-13 22:40
 * @since  ：1.0.0
 * @version：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <br>
 * @description ：
 * <br>
 */
// @formatter:on
public class PageVo<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -5112006617444802L;


    // 页码
    @Schema(description = "页码")
    @Min(value = 1, message = "页码从1开始")
    private long pageNumber = 1;
    // 页大小
    @Schema(description = "页大小")
    @Min(value = 1, message = "页大小最小1")
    @Max(value = 2000, message = "页大小最大2000")
    private long pageSize = 5;
    // 数据总页数
    private long totalPage = 0;
    // 数据总数
    private long totalNumber = 0;
    // 数据分页，开始索引（包含数据）
    private long startIndex = 0;
    // 数据分页，结束索引（不包含数据）
    private long endIndex = 1;
    // 业务分页数据
    private List<T> dataList = new ArrayList<>();
    // 额外的业务数据
    private Map<String, Object> extraDataMap = new HashMap<>();



    public long getPageNumber() {
        return pageNumber;
    }

    public PageVo<T> setPageNumber(@Min(value = 1, message = "页大小最小1") long pageNumber) {
        this.pageNumber = pageNumber;
        this.startIndex = (pageNumber - 1) * pageSize;
        this.endIndex = Math.min(getStartIndex() + pageSize, totalNumber);
        return this;
    }

    public long getPageSize() {
        return pageSize;
    }

    public PageVo<T> setPageSize(@Min(value = 1, message = "页大小从1开始") @Max(value = 2000, message = "页大小最大2000") long pageSize) {
        this.pageSize = pageSize;
        this.startIndex = (pageNumber - 1) * pageSize;
        this.endIndex = Math.min(getStartIndex() + pageSize, totalNumber);
        return this;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public PageVo<T> setTotalNumber(long totalNumber) {
        // 设置总记录数
        this.totalNumber = totalNumber;
        // 设置总页数
        this.totalPage = (totalNumber + pageSize - 1) / pageSize;
        return this;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public Long getEndIndex() {
        return endIndex;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public PageVo<T> setDataList(List<T> dataList) {
        this.dataList = dataList;
        return this;
    }

    public Map<String, Object> getExtraDataMap() {
        return extraDataMap;
    }

    public PageVo<T> setExtraDataMap(Map<String, Object> extraDataMap) {
        this.extraDataMap = extraDataMap;
        return this;
    }


    @Override
    public String toString() {
        return "PageVo{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalNumber=" + totalNumber +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", dataList=" + dataList +
                ", extraDataMap=" + extraDataMap +
                '}';
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public PageVo() {
    }

    public PageVo(long pageNumber, long pageSize, long totalNumber, List<T> dataList, Map<String, Object> extraDataMap) {
        setPageNumber(pageNumber);
        setPageSize(pageSize);
        setTotalNumber(totalNumber);
        this.dataList = dataList;
        this.extraDataMap = extraDataMap;
    }

    public PageVo(long pageNumber, long pageSize, long totalNumber, List<T> dataList) {
        setPageNumber(pageNumber);
        setPageSize(pageSize);
        setTotalNumber(totalNumber);
        this.dataList = dataList;
    }

    public PageVo(long pageNumber, long pageSize) {
        setPageNumber(pageNumber);
        setPageSize(pageSize);
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public void update(List<T> dataList, Map<String, Object> extraDataMap, long totalNumber) {
        this.dataList = dataList;
        this.extraDataMap = extraDataMap;
        setTotalNumber(totalNumber);
    }

    public void update(List<T> dataList, long totalNumber) {
        this.dataList = dataList;
        setTotalNumber(totalNumber);
    }

    public void update(List<T> dataList, Map<String, Object> extraDataMap) {
        this.dataList = dataList;
        this.extraDataMap = extraDataMap;
    }


    // ---------------------------------------------------------------------------------------------------------------------

    public static <T> PageVo<T> newPageVo(Class<T> cl, long pageNumber, long pageSize) {
        PageVo<T> emptyPage = new PageVo<>();
        emptyPage.setPageNumber(pageNumber);
        emptyPage.setPageSize(pageSize);
        return emptyPage;
    }

    // ---------------------------------------------------------------------------------------------------------------------


    public boolean checkPageNumberOutOfRange() {
        // 判断页码是否超过总页数
        return pageNumber > getTotalPage();
    }

}
