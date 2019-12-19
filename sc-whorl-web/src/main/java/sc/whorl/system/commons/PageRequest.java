
package sc.whorl.system.commons;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页业务对象。<br/>

 */
public class PageRequest implements Serializable {

    /**
     * 页码数
     */
    @NotNull
    @Min(1)
    private int pageIndex;

    /**
     * 页面大小
     */
    @NotNull
    @Min(0)
    private int pageSize;

    /**
     * 排序字段，可为空
     */
    private Integer sortBy;

    /**
     * 排序方案，取值有ASC SortDirection.ASC}和{SortDirection.DESC}<br/>
     * 默认值为升序排列
     */
    private SortDirection direction = SortDirection.ASC;

    /**
     * 默认构造函数
     */
    public PageRequest() {
    }

    /**
     * 通过页码和页大小构造分页条件对象。
     *
     * @param pageIndex
     *         页码数
     * @param pageSize
     *         页大小
     */
    public PageRequest(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    /**
     * 通过排序标记和排序规则构建排序对象。
     *
     * @param sortBy
     *         排序标记
     * @param direction
     *         排序规则
     */
    public PageRequest(Integer sortBy, SortDirection direction) {
        this.sortBy = sortBy;
        this.direction = direction;
    }

    /**
     * 通过页码和页大小、排序标记和排序规则分页排序对象。
     *
     * @param pageIndex
     *         页码
     * @param pageSize
     *         页大小
     * @param sortBy
     *         排序标记
     * @param direction
     *         排序规则
     */
    public PageRequest(int pageIndex, int pageSize, Integer sortBy, SortDirection direction) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.direction = direction;
    }

    /**
     * 获取页码数
     *
     * @return 页码数
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置页码数
     *
     * @param pageIndex
     *         页码数
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * 获取页面大小
     *
     * @return 页面大小
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 获取页面大小
     *
     * @param pageSize
     *         页面大小
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置排序方案
     *
     * @return 排序方案
     */
    public Integer getSortBy() {
        return sortBy;
    }

    /**
     * 获取排序方案
     *
     * @param sortBy
     *         排序方案
     */
    public void setSortBy(Integer sortBy) {
        this.sortBy = sortBy;
    }


    public SortDirection getDirection() {
        return direction;
    }


    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }
}
