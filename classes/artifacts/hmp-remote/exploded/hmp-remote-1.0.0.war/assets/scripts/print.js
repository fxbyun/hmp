/**
 * Created by iceyanbin on 2015-12-13.
 */
//var YJZ_Printer = {
//    printUrl: function (url, count) {
//        var LODOP = getLodop();
//        LODOP.PRINT_INITA("0mm", "0mm", "120mm", "100%", "YJZ_printer");
//        if (count) {
//            LODOP.SET_PRINT_COPIES(count);
//        } else {
//            LODOP.SET_PRINT_COPIES(1);
//        }
//        LODOP.ADD_PRINT_URL("2mm", "5mm", "100%", "100%", url);
//        LODOP.PREVIEW();
//        //LODOP.PRINT();
//    }
//};

//自适应
//var YJZ_Printer = {
//    printUrl: function (url, count) {
//        var LODOP = getLodop();
//        LODOP.PRINT_INITA("0mm", "0mm", "120mm", "100%", "YJZ_printer");
//        if (count) {
//            LODOP.SET_PRINT_COPIES(count);
//        } else {
//            LODOP.SET_PRINT_COPIES(1);
//        }
//        LODOP.ADD_PRINT_URL("2mm", "5mm", "100%", "100%", url);
//        LODOP.PREVIEW();
//        //LODOP.PRINT();
//    }
//};


/*
 A5纸大小
 */
var YJZ_Printer = {
    printUrl: function (url, count) {          //
        try {
            var LODOP = getLodop();
            LODOP.PRINT_INITA("0mm", "31mm", "140mm", "220mm", "YJZ_printer");
            if (count) {
                LODOP.SET_PRINT_COPIES(count);
            } else {
                LODOP.SET_PRINT_COPIES(1);                                   //
            }
            LODOP.ADD_PRINT_URL("2mm", "0mm", "100%", "100%", url);
            //LODOP.SET_PRINT_STYLEA(0, "IDTagForPick", "diii");//设置属性id，读取页面部分内容打印(6.1.9.4版本后支持该功能)
            //LODOP.PREVIEW();
            //LODOP.PRINT_DESIGN()
            LODOP.PRINT();
        } catch (e) {
            //    Alert.error("打印插件初始化异常,请检查打印插件是否正常安装!")
            alert("打印控件异常")
        }

    },
    print80Url: function (url, count) {
        var LODOP = getLodop();
        try {
            LODOP.PRINT_INITA("0mm", "0mm", "120mm", "100%", "YJZ_printer");
            if (count) {
                LODOP.SET_PRINT_COPIES(count);
            } else {
                LODOP.SET_PRINT_COPIES(1);
            }
            LODOP.ADD_PRINT_URL("2mm", "5mm", "100%", "100%", url);
            //LODOP.PREVIEW();
            //LODOP.PRINT_DESIGN()
            LODOP.PRINT();
        } catch (e) {
            //Alert.error("打印插件初始化异常,请检查打印插件是否正常安装!")
        }

    }
};