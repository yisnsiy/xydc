package com.yisn.modules.vo;

import com.yisn.modules.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by yisn on 2021/04/22/15:02
 */
@Data
@AllArgsConstructor
public class StoreVO extends Store {
    private String[] licensesFile;
    private String[] kitchenPictureFile;

    public void VoConvertPojo() {
        this.setLicenses(null);
        this.setKitchenPicture(null);
        if(licensesFile.length > 0) {
            String str = "";
            for(int i = 0; i < licensesFile.length; ++ i) {
                str = str + licensesFile[i] + ';';
            }
            this.setLicenses(str);
        }
        if(kitchenPictureFile.length > 0) {
            String str = "";
            for(int i = 0; i < kitchenPictureFile.length; ++ i) {
                str = str + kitchenPictureFile[i] + ';';
            }
            this.setKitchenPicture(str);
        }
    }
    public void PojoConvertVo() {
        this.setLicensesFile(null);
        this.setKitchenPictureFile(null);
        String str = this.getLicenses();
        while(str.length() > 0 && str.equals("")) {
            int pos = str.indexOf(';');
            this.licensesFile[this.licensesFile.length] = str.substring(0, pos);
            str = str.substring(pos + 1, str.length());
        }

        str = this.getKitchenPicture();
        while(str.length() > 0 && str.equals("")) {
            int pos = str.indexOf(';');
            this.kitchenPictureFile[this.kitchenPictureFile.length] = str.substring(0, pos);
            str = str.substring(pos + 1, str.length());
        }
    }
}
