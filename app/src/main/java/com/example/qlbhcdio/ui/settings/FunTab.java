package com.example.qlbhcdio.ui.settings;

public enum FunTab {
    /**label**/
    LB_ACCOUNT(0, "Thiết lập tài khoản"),
    LB_SETTING(-1, "Cài đặt ứng dụng"),

    /**function**/
    FUN_PAYMENT(1, "Thanh Toán"),
    FUN_PASSWORD(2, "Mật khẩu"),
    FUN_LANGUAGE(3, "Ngôn ngữ"),
    FUN_NOTIFICATION(4, "Thông báo"),
    FUN_LOGOUT(5, "Đăng Xuất");

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private int id;
    private String name;

     FunTab(int id, String name) {
        this.id = id;
        this.name = name;
    }
     public static int getTypeLabel (){
         return 0;
     }
     public static int getTypeFun(){
         return 1;
     }
}
