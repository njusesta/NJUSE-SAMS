package org.nju.sesta.sams.parameter.PersonalInfo;

import org.nju.sesta.sams.entity.DevAxFormItem;

import java.util.List;

public class DevFormInfoParameter {
    private List<DevAxFormItem> devAxForm;

    public List<DevAxFormItem> getDevAxForm() {
        return devAxForm;
    }

    public void setDevAxForm(List<DevAxFormItem> devAxForm) {
        this.devAxForm = devAxForm;
    }
}
