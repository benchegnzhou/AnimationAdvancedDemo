package com.ztsc.house.bean.loacation;

import java.util.List;

/**
 * Created by benchengzhou on 2017/4/25  17:18 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： LocationSelectionBeen
 * 备    注：  地图选择当前位置
 */

public class LocationSelectionBeen {


    /**
     * status : E0
     * time : 0.0
     * count : 1
     * list : [{"province":{"name":"四川省","ename":"Sichuan","code":"510000000000","x":"104.066","y":"30.6595","direction":"NorthWest","distance":"5713.78"},"city":{"name":"成都市","ename":"Chengdu","code":"510100000000","x":"104.066","y":"30.6595","direction":"NorthWest","distance":"5713.78","tel":"028","citycode":"028"},"district":{"name":"锦江区","ename":"Jinjiang","code":"510104000000","x":"104.081","y":"30.6577","direction":"North","distance":"5033.13"},"roadlist":[],"poilist":[{"name":"五桂园","ename":"Wuguiyuan","code":"510104","type":"","telephone":"","address":"四川省;成都市;锦江区;华润路;3","x":"104.09248","y":"30.613519999999998","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;3 Huarun Road","direction":"Center","distance":"0"},{"name":"链家地产","ename":"Home Link Real Estate","code":"510104","type":"","telephone":"","address":"四川省;成都市;锦江区;华润路;80","x":"104.09246","y":"30.613509999999998","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;80 Huarun Rd","direction":"SouthWest","distance":"2.2136"},{"name":"链家地产","ename":"Home Link Real Estate","code":"510104","type":"","telephone":"","address":"四川省;成都市;锦江区;华润路;80","x":"104.09246","y":"30.613509999999998","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;80 Huarun Rd","direction":"SouthWest","distance":"2.2136"},{"name":"西克莫手工烘焙","ename":"Xikemo Manual Bakery","code":"510104","type":"","telephone":"18200451520","address":"四川省;成都市;锦江区;华润路;86","x":"104.09255","y":"30.61355","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;86 Huarun Rd","direction":"NorthEast","distance":"7.48375"},{"name":"华润路3号院(南门)","ename":"","code":"510104","type":"120302","telephone":"","address":"四川省;成都市;锦江区;华润路;3;","x":"104.09245756083301","y":"30.6136064958333","poiweight":"0.6","pguid":"","eaddress":"","direction":"North","distance":"9.84481"},{"name":"五星精典洗衣","ename":"Fscimo Laundry","code":"510104","type":"","telephone":"028-83136011","address":"四川省;成都市;锦江区;华润路;3号;附13号","x":"104.09238","y":"30.61348","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;No.13 Attach No.3 Huarun Road","direction":"West","distance":"10.5533"},{"name":"吉乐超市","ename":"","code":"510104","type":"060200","telephone":"028-84520063|84534959","address":"四川省;成都市;锦江区;华润路;3号;附15-16号;","x":"104.092545673889","y":"30.6136320938889","poiweight":"0.3","pguid":"","eaddress":"","direction":"NorthEast","distance":"13.9674"},{"name":"蓝剑水连锁翡翠城体验店","ename":"Bluesword Water Chain Emerald Cheng Experience Shop","code":"510104","type":"","telephone":"028-89035855","address":"四川省;成都市;锦江区;华润路;3号;附12号","x":"104.09235","y":"30.61346","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;No.3-12 Huarun Road","direction":"SouthWest","distance":"14.1173"},{"name":"自贡好吃客","ename":"Zigong Haochike","code":"510104","type":"","telephone":"","address":"四川省;成都市;锦江区;华润路;88","x":"104.09262","y":"30.61358","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;88 Huarun Rd","direction":"NorthEast","distance":"14.9675"},{"name":"黄牛肉汤锅王","ename":"Beef Soup King","code":"510104","type":"","telephone":"028-68963837","address":"四川省;成都市;锦江区;华润路;88","x":"104.09262","y":"30.61358","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;88 Huarun Rd","direction":"NorthEast","distance":"14.9675"}]}]
     * version : v2.0.0
     * type : list
     * ver : v2.0.0
     */

    private String status;
    private String time;
    private int count;
    private String version;
    private String type;
    private String ver;
    private List<ListBean> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * province : {"name":"四川省","ename":"Sichuan","code":"510000000000","x":"104.066","y":"30.6595","direction":"NorthWest","distance":"5713.78"}
         * city : {"name":"成都市","ename":"Chengdu","code":"510100000000","x":"104.066","y":"30.6595","direction":"NorthWest","distance":"5713.78","tel":"028","citycode":"028"}
         * district : {"name":"锦江区","ename":"Jinjiang","code":"510104000000","x":"104.081","y":"30.6577","direction":"North","distance":"5033.13"}
         * roadlist : []
         * poilist : [{"name":"五桂园","ename":"Wuguiyuan","code":"510104","type":"","telephone":"","address":"四川省;成都市;锦江区;华润路;3","x":"104.09248","y":"30.613519999999998","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;3 Huarun Road","direction":"Center","distance":"0"},{"name":"链家地产","ename":"Home Link Real Estate","code":"510104","type":"","telephone":"","address":"四川省;成都市;锦江区;华润路;80","x":"104.09246","y":"30.613509999999998","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;80 Huarun Rd","direction":"SouthWest","distance":"2.2136"},{"name":"链家地产","ename":"Home Link Real Estate","code":"510104","type":"","telephone":"","address":"四川省;成都市;锦江区;华润路;80","x":"104.09246","y":"30.613509999999998","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;80 Huarun Rd","direction":"SouthWest","distance":"2.2136"},{"name":"西克莫手工烘焙","ename":"Xikemo Manual Bakery","code":"510104","type":"","telephone":"18200451520","address":"四川省;成都市;锦江区;华润路;86","x":"104.09255","y":"30.61355","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;86 Huarun Rd","direction":"NorthEast","distance":"7.48375"},{"name":"华润路3号院(南门)","ename":"","code":"510104","type":"120302","telephone":"","address":"四川省;成都市;锦江区;华润路;3;","x":"104.09245756083301","y":"30.6136064958333","poiweight":"0.6","pguid":"","eaddress":"","direction":"North","distance":"9.84481"},{"name":"五星精典洗衣","ename":"Fscimo Laundry","code":"510104","type":"","telephone":"028-83136011","address":"四川省;成都市;锦江区;华润路;3号;附13号","x":"104.09238","y":"30.61348","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;No.13 Attach No.3 Huarun Road","direction":"West","distance":"10.5533"},{"name":"吉乐超市","ename":"","code":"510104","type":"060200","telephone":"028-84520063|84534959","address":"四川省;成都市;锦江区;华润路;3号;附15-16号;","x":"104.092545673889","y":"30.6136320938889","poiweight":"0.3","pguid":"","eaddress":"","direction":"NorthEast","distance":"13.9674"},{"name":"蓝剑水连锁翡翠城体验店","ename":"Bluesword Water Chain Emerald Cheng Experience Shop","code":"510104","type":"","telephone":"028-89035855","address":"四川省;成都市;锦江区;华润路;3号;附12号","x":"104.09235","y":"30.61346","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;No.3-12 Huarun Road","direction":"SouthWest","distance":"14.1173"},{"name":"自贡好吃客","ename":"Zigong Haochike","code":"510104","type":"","telephone":"","address":"四川省;成都市;锦江区;华润路;88","x":"104.09262","y":"30.61358","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;88 Huarun Rd","direction":"NorthEast","distance":"14.9675"},{"name":"黄牛肉汤锅王","ename":"Beef Soup King","code":"510104","type":"","telephone":"028-68963837","address":"四川省;成都市;锦江区;华润路;88","x":"104.09262","y":"30.61358","poiweight":"0.0","pguid":"","eaddress":"Jinjiang;88 Huarun Rd","direction":"NorthEast","distance":"14.9675"}]
         */

        private ProvinceBean province;
        private CityBean city;
        private DistrictBean district;
        private List<?> roadlist;
        private List<PoilistBean> poilist;

        public ProvinceBean getProvince() {
            return province;
        }

        public void setProvince(ProvinceBean province) {
            this.province = province;
        }

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public DistrictBean getDistrict() {
            return district;
        }

        public void setDistrict(DistrictBean district) {
            this.district = district;
        }

        public List<?> getRoadlist() {
            return roadlist;
        }

        public void setRoadlist(List<?> roadlist) {
            this.roadlist = roadlist;
        }

        public List<PoilistBean> getPoilist() {
            return poilist;
        }

        public void setPoilist(List<PoilistBean> poilist) {
            this.poilist = poilist;
        }

        public static class ProvinceBean {
            /**
             * name : 四川省
             * ename : Sichuan
             * code : 510000000000
             * x : 104.066
             * y : 30.6595
             * direction : NorthWest
             * distance : 5713.78
             */

            private String name;
            private String ename;
            private String code;
            private String x;
            private String y;
            private String direction;
            private String distance;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }

        public static class CityBean {
            /**
             * name : 成都市
             * ename : Chengdu
             * code : 510100000000
             * x : 104.066
             * y : 30.6595
             * direction : NorthWest
             * distance : 5713.78
             * tel : 028
             * citycode : 028
             */

            private String name;
            private String ename;
            private String code;
            private String x;
            private String y;
            private String direction;
            private String distance;
            private String tel;
            private String citycode;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getCitycode() {
                return citycode;
            }

            public void setCitycode(String citycode) {
                this.citycode = citycode;
            }
        }

        public static class DistrictBean {
            /**
             * name : 锦江区
             * ename : Jinjiang
             * code : 510104000000
             * x : 104.081
             * y : 30.6577
             * direction : North
             * distance : 5033.13
             */

            private String name;
            private String ename;
            private String code;
            private String x;
            private String y;
            private String direction;
            private String distance;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }

        public static class PoilistBean {
            /**
             * name : 五桂园
             * ename : Wuguiyuan
             * code : 510104
             * type :
             * telephone :
             * address : 四川省;成都市;锦江区;华润路;3
             * x : 104.09248
             * y : 30.613519999999998
             * poiweight : 0.0
             * pguid :
             * eaddress : Jinjiang;3 Huarun Road
             * direction : Center
             * distance : 0
             */

            private String name;
            private String ename;
            private String code;
            private String type;
            private String telephone;
            private String address;
            private String x;
            private String y;
            private String poiweight;
            private String pguid;
            private String eaddress;
            private String direction;
            private String distance;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }

            public String getPoiweight() {
                return poiweight;
            }

            public void setPoiweight(String poiweight) {
                this.poiweight = poiweight;
            }

            public String getPguid() {
                return pguid;
            }

            public void setPguid(String pguid) {
                this.pguid = pguid;
            }

            public String getEaddress() {
                return eaddress;
            }

            public void setEaddress(String eaddress) {
                this.eaddress = eaddress;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }
    }
}
