package org.quickstart.example.design.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:17
 * @version v1.0
 */

@Data
@AllArgsConstructor
public class Receipt {

    /**
     * 回执信息
     */
    String message;

    /**
     * 回执类型(`MT1101、MT2101、MT4101、MT8104、MT8105、MT9999`)
     */
    String type;

}
