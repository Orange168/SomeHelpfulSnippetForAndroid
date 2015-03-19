//data 2015-03-19 from video http://www.imooc.com/learn/302
//色调
ColorMatrix hueMatrix = new ColorMatrix() ;
hueMatrix.setRotate(0, hue) ;// 0: 1: 2: 
//饱和度
ColorMatrix saturationMatrix = new ColorMatrix() ;
saturationMatrix.setSaturation(saturation) ;
//亮度
ColorMatrix lumMatrix = new ColorMatrix() ;
lumMatrix.setScale(lum,lum,lum.1) ;
//utilize
ColorMatrix imageMatrix = new ColorMatrix() ;
imageMatrix.postConcat(hueMatrix) ;
imageMatrix.postConcat(saturationMatrix) ;
imageMatrix.postConcat(lumMatrix) ;
Paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix)) ;
