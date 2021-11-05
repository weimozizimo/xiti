package tests;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class Test2 {
    public static void main(String[] args) {
        IPage<JSONObject> query = new Page<>();

        JSONObject page = new JSONObject();
        page.set("total",query.getTotal());
        page.set("pageSize",query.getRecords().size());
        page.set("pageNum",query.getCurrent());
    }
}
