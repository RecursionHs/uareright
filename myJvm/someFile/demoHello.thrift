namespace java com.hs.thrift.demo
 
service  HelloWorldService {
  string sayHello(1:string username)
}