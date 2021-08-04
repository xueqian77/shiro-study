package cn.x.study.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "role")
public class Role {

    @TableId
    private Long id;

    @TableField(value = "role_name")
    private String roleName;

    @TableField(exist=false)
    private Set<Permissions> permissionsSet;

}
