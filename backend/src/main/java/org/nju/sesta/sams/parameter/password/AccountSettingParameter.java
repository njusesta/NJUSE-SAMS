package org.nju.sesta.sams.parameter.password;

import java.util.function.Supplier;

public class AccountSettingParameter {
    private String id;
    private String password;
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * 检查是否所有的字段都为非null值
     *
     * @param exceptionSupplier 异常提供器
     * @param <X>               可抛出的异常
     * @throws X 若有字段为null，则抛出异常
     */
    public <X extends Throwable> void checkNotNull(Supplier<? extends X> exceptionSupplier) throws X {
        if (id == null || password == null || code == null)
            throw exceptionSupplier.get();
    }

    /**
     * 检查除了id是否所有的字段都为非null值
     *
     * @param exceptionSupplier 异常提供器
     * @param <X>               可抛出的异常
     * @throws X 若除了id有字段为null，则抛出异常
     */
    public <X extends Throwable> void checkNotNullIgnoreId(Supplier<? extends X> exceptionSupplier) throws X {
        if (password == null || code == null)
            throw exceptionSupplier.get();
    }

}
