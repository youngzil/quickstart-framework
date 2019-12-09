package org.quickstart.easyexcel.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.quickstart.easyexcel.TestFileUtil;

/**
 * 写的常见写法
 *
 * @author Jiaju Zhuang
 */
@Ignore
public class WriteTest {

  /**
   * 最简单的写
   * <p>
   * 1. 创建excel对应的实体对象 参照{@link DemoData}
   * <p>
   * 2. 直接写即可
   */
  @Test
  public void simpleWrite() {
    // 写法1
    String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
    // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
    // 如果这里想使用03 则 传入excelType参数即可
    EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

    // 写法2
    fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
    // 这里 需要指定写用哪个class去写
    ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
    WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
    excelWriter.write(data(), writeSheet);
    /// 千万别忘记finish 会帮忙关闭流
    excelWriter.finish();
  }

  /**
   * 根据参数只导出指定列
   * <p>
   * 1. 创建excel对应的实体对象 参照{@link DemoData}
   * <p>
   * 2. 根据自己或者排除自己需要的列
   * <p>
   * 3. 直接写即可
   *
   * @since 2.1.1
   */
  @Test
  public void excludeOrIncludeWrite() {
    String fileName = TestFileUtil.getPath() + "excludeOrIncludeWrite" + System.currentTimeMillis() + ".xlsx";

    // 根据用户传入字段 假设我们要忽略 date
    Set<String> excludeColumnFiledNames = new HashSet<String>();
    excludeColumnFiledNames.add("date");
    // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
    EasyExcel.write(fileName, DemoData.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("模板")
        .doWrite(data());

    fileName = TestFileUtil.getPath() + "excludeOrIncludeWrite" + System.currentTimeMillis() + ".xlsx";
    // 根据用户传入字段 假设我们只要导出 date
    Set<String> includeColumnFiledNames = new HashSet<String>();
    includeColumnFiledNames.add("date");
    // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
    EasyExcel.write(fileName, DemoData.class).includeColumnFiledNames(includeColumnFiledNames).sheet("模板")
        .doWrite(data());
  }


  private List<List<String>> head() {
    List<List<String>> list = new ArrayList<List<String>>();
    List<String> head0 = new ArrayList<String>();
    head0.add("字符串" + System.currentTimeMillis());
    List<String> head1 = new ArrayList<String>();
    head1.add("数字" + System.currentTimeMillis());
    List<String> head2 = new ArrayList<String>();
    head2.add("日期" + System.currentTimeMillis());
    list.add(head0);
    list.add(head1);
    list.add(head2);
    return list;
  }

  private List<List<Object>> dataList() {
    List<List<Object>> list = new ArrayList<List<Object>>();
    for (int i = 0; i < 10; i++) {
      List<Object> data = new ArrayList<Object>();
      data.add("字符串" + i);
      data.add(new Date());
      data.add(0.56);
      list.add(data);
    }
    return list;
  }

  private List<DemoData> data() {
    List<DemoData> list = new ArrayList<DemoData>();
    for (int i = 0; i < 10; i++) {
      DemoData data = new DemoData();
      data.setString("字符串" + i);
      data.setDate(new Date());
      data.setDoubleData(0.56);
      list.add(data);
    }
    return list;
  }

}
