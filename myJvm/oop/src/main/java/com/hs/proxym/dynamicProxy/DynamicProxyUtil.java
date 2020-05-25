package com.hs.proxym.dynamicProxy;

import com.hs.proxym.staticProxy.combinationType.Eat;
import com.hs.proxym.staticProxy.combinationType.EatImpl;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.UUID;

/**
 * @author : sk
 * @version V1.0
 * @Project: myJvm
 * @Package com.hs.proxym.dynamicProxy
 * @Description: 模拟动态代理
 * 用字符串拼接成一个java类，在编译成.class文件，再反射调用
 * @date Date : 2020年05月25日 下午10:28
 */
public class DynamicProxyUtil {
    private static String lineBreak = System.getProperty("line.separator");
    private static String  tabStr = "\t";

    public static Object newInstance(Object target){
        //随机一个代理类名
        String proxyName =UUID.randomUUID().toString().substring(0,6);
        StringBuilder contentSb = new StringBuilder();
        Object proxy = null;
        //默认取第一个接口
        Class interfaceClass = target.getClass().getInterfaces()[0];
        Method[] declaredMethods = interfaceClass.getDeclaredMethods();
        //接口名
        String infName = interfaceClass.getSimpleName();
        //当前包名路径
        String packageStr = interfaceClass.getPackage().toString() + ";" +lineBreak;
        //inport接口信息
        String inportStr = "import " + interfaceClass.getName() + ";" +lineBreak;
        //类信息
        String classFirstLine = "public class $Proxy" + proxyName + " implements " + infName + "{" + lineBreak;
        String filedStr = tabStr + "private " + infName + " target;" + lineBreak;
        String constractStr = tabStr + "public $Proxy" + proxyName + "(" + infName + " target){" + lineBreak
                + tabStr + tabStr + "this.target = target;" + lineBreak
                + tabStr + "}" + lineBreak;
        String methodContenet  = "";

        for(Method method : declaredMethods){
            //返回类型
            String retTypeStr = method.getReturnType().getSimpleName();
            //方法名
            String methodName = method.getName();
            //方法参数
            Class<?>[] parameterTypes = method.getParameterTypes();
            String argsContent = "";
            String paramContent = "";
            String tmpStr = "";
            int aragsLenth = parameterTypes.length;
            if(aragsLenth >= 1){
                for(int i = 0;i < aragsLenth;i++){
                    //参数类型
                    tmpStr = parameterTypes[i].getSimpleName();
                    argsContent += tmpStr + " p" + i + ",";
                    paramContent += "p" + i + ",";
                }
            }
            //处理掉最后一个逗号
            if(argsContent.length() > 0){
                argsContent.substring(0,argsContent.lastIndexOf(",") - 1);
                paramContent.substring(0,paramContent.lastIndexOf(",") - 1);
            }

            methodContenet += tabStr + "public " + retTypeStr + " " +methodName + "(" + argsContent + " ){" + lineBreak
                    +tabStr + tabStr + "System.out.println(\"增强方法...\");" + lineBreak
                    +tabStr + tabStr + "target." + methodName + "(" + paramContent + ");" + lineBreak
                    +tabStr + "}" +lineBreak;
            contentSb.append(packageStr)
                     .append(inportStr)
                     .append(classFirstLine)
                     .append(filedStr)
                     .append(constractStr)
                     .append(methodContenet)
                     .append("}")
                     .append(lineBreak);

        }

        System.out.println(contentSb.toString());
        //将包路径转为文件路径
        String fileDir = packageStr.split(" ")[1]
                .replace(lineBreak,"")
                .replace(".", File.separator)
                .replace(";", File.separator);
        String homeDir = target.getClass().getResource("/").getPath();
        String fileStr = homeDir + fileDir;
        fileStr = fileStr + "$Proxy" + proxyName + ".java";

        //生成java文件
        File file = new File(fileStr);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(contentSb.toString());
            fw.flush();
            fw.close();


        //获取class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(file);

        JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        t.call();
        fileMgr.close();

        String className = "$Proxy" + proxyName;

        URL[] urls = new URL[1];

        ///home/hsir/IdeaProjects/uareright/myJvm/oop/target/classe
        homeDir = homeDir.substring(0,homeDir.lastIndexOf(File.separator) -1 );
        System.out.println(homeDir);

        String  packegeName= packageStr.split(" ")[1]
                .replace(lineBreak,"")
                .replace(";","");

        //com.hs.proxym.staticProxy.combinationType
        System.out.println(packegeName);

        urls[0] = new URL("file",null,homeDir);

        URLClassLoader loader = new URLClassLoader(urls);

        loader.loadClass(packegeName + "." + className);


        proxy = loader.loadClass(packegeName + "." + className);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return proxy;
    }

    public static void main(String[] args) {
        Object ei = newInstance(new EatImpl());
        //有点问题，后面解决
        System.out.println(ei);
        //ei.eat();
    }
}
