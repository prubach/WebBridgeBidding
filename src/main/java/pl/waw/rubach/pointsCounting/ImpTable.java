package pl.waw.rubach.pointsCounting;

public class ImpTable {

   int results;
   ImpTable(int pointDifferent){
       int result;
       if (pointDifferent>=4000) result = 24;
       else if (pointDifferent>=3500) result = 23;
       else if (pointDifferent>=3000) result = 22;
       else result=0;


       this.results = result;
   }

}
