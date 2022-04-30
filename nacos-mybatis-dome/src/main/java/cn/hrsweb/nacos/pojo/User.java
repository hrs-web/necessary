package cn.hrsweb.nacos.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("tb_user")
@Data
public class User {
    private Long id;      // id
    private String grade;  // 班级
    private Integer number;  // 人数
    private Date hesuanDate;  // 核酸时间
}
