package cn.x.study.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName(value = "permissions")
public class Permissions {
    private Long id;
    private String permissionsName;
}
