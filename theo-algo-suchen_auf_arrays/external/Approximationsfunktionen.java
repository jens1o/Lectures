import java.util.*;

public class Approximationsfunktionen
{
   private static final int SP = 4;

   public static void main( String[] args )
   {
      System.out.println( "Approximationsfunktionen zur Inter- und Extrapolation\n" +
            "Kommentare siehe:\n" +
            "http://www.torsten-horn.de/techdocs/java-approximationsfunktionen.htm" );

      if( args == null || args.length < 2 || args.length % 2 != 0 )
      {
         System.out.println( "\nBitte eine Menge an x-/y-Wertepaaren angeben " +
               "(Werte durch Leerzeichen getrennt)." );
         return;
      }

      double[] xyArr = convertStringArrToDoubleArr( args );
      ArrayList<RegressionResult> result = new ArrayList<RegressionResult>();

      // Verschiedene Regressionen:
      result.add( calculateLinearRegression( xyArr ) );
      result.add( calculatePowerRegression( xyArr ) );
      result.add( calculateLogarithmicRegression( xyArr ) );
      result.add( calculateExponentialRegression( xyArr ) );
      result.add( calculateOneMinusExponentialRegression( xyArr ) );

      boolean atLeastOne = false;
      for( Iterator<RegressionResult> itr = result.iterator(); itr.hasNext(); ) {
         RegressionResult res = itr.next();
         atLeastOne |= res != null;
         if( res != null ) System.out.println( "\n" +
               linksbuendigerString( res.titel + ":", "          " ) +
               linksbuendigerString( res.formel, "                                         " ) +
               "  (Bestimmtheitsmass = " + res.rr + ")" );
      }

      if( atLeastOne ) {
         System.out.print( "\nx         y           " );
         for( Iterator<RegressionResult> itr = result.iterator(); itr.hasNext(); ) {
            RegressionResult res = itr.next();
            if( res != null ) System.out.print(
                  linksbuendigerString( res.titel, "          " ) );
         }
         System.out.println();
         for( int i=0; i < args.length && i < 20; i+=2 ) {
            System.out.print( linksbuendigerString( "x=" + args[i] + ",", "         " ) +
                  linksbuendigerString( " y=" + args[i+1] + ":", "            " ) );
            for( Iterator<RegressionResult> itr = result.iterator(); itr.hasNext(); ) {
               RegressionResult res = itr.next();
               if( res != null ) System.out.print( linksbuendigerString(
                     " " + roundSignificant( res.approxFunction.execute( res.a, res.b, xyArr[i] ), SP ),
                     "          " ) );
            }
            System.out.println();
         }
         if( args.length > 20 ) {
            System.out.println( "..." );
         }
      }
   }

   // Lineare Regression
   // y = a + b * x
   static RegressionResult calculateLinearRegression( double[] xyArr )
   {
      if( xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0 ) return null;

      int    n   = xyArr.length / 2;
      double xs  = 0;
      double ys  = 0;
      double xqs = 0;
      double yqs = 0;
      double xys = 0;

      for( int i=0; i < xyArr.length; i+=2 ) {
         xs  += xyArr[i];
         ys  += xyArr[i+1];
         xqs += xyArr[i]   * xyArr[i];
         yqs += xyArr[i+1] * xyArr[i+1];
         xys += xyArr[i]   * xyArr[i+1];
      }

      RegressionResult abr = new RegressionResult();
      double xm  = xs / n;
      double ym  = ys / n;
      double xv  = xqs / n - (xm * xm);
      double yv  = yqs / n - (ym * ym);
      double kv  = xys / n - (xm * ym);
      abr.rr     = Math.min( (kv * kv) / (xv * yv), 1 );
      abr.b      = kv / xv;
      abr.a      = ym - abr.b * xm;
      abr.titel  = "Lin";
      abr.formel = "y = " + roundSignificant( abr.a, SP ) + " + " + roundSignificant( abr.b, SP ) + " * x";
      abr.approxFunction = new ApproxFunction() {
         public double execute( double a, double b, double x ) {
            return a + b * x;
         }
      };

      return abr;
   }

   // Potenzielle Regression
   // y = a * x^b
   // Regression ueber: ln(y) = ln(a) + b * ln(x)
   static RegressionResult calculatePowerRegression( double[] xyArr )
   {
      if( xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0 ) return null;

      double[] xyArrConv = new double[xyArr.length];

      for( int i=0; i < xyArr.length; i+=2 ) {
         if( xyArr[i] <= 0 || xyArr[i+1] <= 0 ) return null;
         xyArrConv[i]   = Math.log( xyArr[i]   );
         xyArrConv[i+1] = Math.log( xyArr[i+1] );
      }

      RegressionResult abr = calculateLinearRegression( xyArrConv );
      if( abr == null ) return null;
      abr.a      = Math.exp( abr.a );
      abr.titel  = "Pow";
      abr.formel = "y = " + roundSignificant( abr.a, SP ) + " * x ^ " + roundSignificant( abr.b, SP );
      abr.approxFunction = new ApproxFunction() {
         public double execute( double a, double b, double x ) {
            return a * Math.pow( x, b );
         }
      };

      return abr;
   }

   // Logarithmische Regression
   // y = a + b * ln(x)
   static RegressionResult calculateLogarithmicRegression( double[] xyArr )
   {
      if( xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0 ) return null;

      double[] xyArrConv = new double[xyArr.length];

      for( int i=0; i < xyArr.length; i+=2 ) {
         if( xyArr[i] <= 0 ) return null;
         xyArrConv[i]   = Math.log( xyArr[i] );
         xyArrConv[i+1] = xyArr[i+1];
      }

      RegressionResult abr = calculateLinearRegression( xyArrConv );
      if( abr == null ) return null;
      abr.titel  = "Log";
      abr.formel = "y = " + roundSignificant( abr.a, SP ) + " + " + roundSignificant( abr.b, SP ) + " * ln(x)";
      abr.approxFunction = new ApproxFunction() {
         public double execute( double a, double b, double x ) {
            return a + b * Math.log( x );
         }
      };

      return abr;
   }

   // Exponentielle Regression
   // y = a * e^(b * x)
   // Regression ueber: ln(y) = ln(a) + b * x
   static RegressionResult calculateExponentialRegression( double[] xyArr )
   {
      if( xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0 ) return null;

      double[] xyArrConv = new double[xyArr.length];

      for( int i=0; i < xyArr.length; i+=2 ) {
         if( xyArr[i+1] <= 0 ) return null;
         xyArrConv[i]   = xyArr[i];
         xyArrConv[i+1] = Math.log( xyArr[i+1] );
      }

      RegressionResult abr = calculateLinearRegression( xyArrConv );
      if( abr == null ) return null;
      abr.a      = Math.exp( abr.a );
      abr.titel  = "Exp";
      abr.formel = "y = " + roundSignificant( abr.a, SP ) + " * e ^ (" + roundSignificant( abr.b, SP ) + " * x)";
      abr.approxFunction = new ApproxFunction() {
         public double execute( double a, double b, double x ) {
            return a * Math.exp( b * x );
         }
      };

      return abr;
   }

   // Gespiegelte und verschobene exponentielle Regression
   // y = a * ( 1 - e^(-b * x) )
   // Approximationsfunktion beginnt bei 0 und strebt gegen den Grenzwert "limit".
   // Falls "limit" nicht bekannt ist: Iterativ naehern.
   static RegressionResult calculateOneMinusExponentialRegression( double[] xyArr, double limit )
   {
      double[] xyArrTest = new double[xyArr.length];

      for( int i = 0; i < xyArr.length; i+=2 ) {
         xyArrTest[i]   = -xyArr[i];
         xyArrTest[i+1] = limit - xyArr[i+1];
      }

      RegressionResult abr = calculateExponentialRegression( xyArrTest );
      if( abr == null ) return null;
      abr.a = limit;
      return abr;
   }

   // Gespiegelte und verschobene exponentielle Regression
   // y = a * ( 1 - e^(-b * x) )
   // Approximationsfunktion beginnt bei 0 und strebt gegen den Grenzwert "limit".
   static RegressionResult calculateOneMinusExponentialRegression( double[] xyArr )
   {
      final double INCR_FACTOR = 1.001;
      double yMax = 0;
      if( xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0 ) return null;

      for( int i = 1; i < xyArr.length; i+=2 ) yMax = Math.max( yMax, xyArr[i] );

      double lim = searchMaximumFromFunctionFromX( yMax, INCR_FACTOR, xyArr,
            new FunctionFromX() {
               public double execute( double x, Object helpObject ) {
                  RegressionResult abr = calculateOneMinusExponentialRegression( (double[]) helpObject, x );
                  if( abr == null ) return 0;
                  return abr.rr;
               }
            });

      RegressionResult abr = calculateOneMinusExponentialRegression( xyArr, lim );

      if( abr == null ) return null;
      abr.titel  = "1_E";
      abr.formel = "y = " + roundSignificant( abr.a, SP ) +
                   " * ( 1 - e ^ (-" + roundSignificant( abr.b, SP ) + " * x) )";
      abr.approxFunction = new ApproxFunction() {
         public double execute( double a, double b, double x ) {
            return a * ( 1 - Math.exp( -b * x ) );
         }
      };

      return abr;
   }

   // Suche den x-Wert fuer den die "FunctionFromX" ein Maximum hat
   static double searchMaximumFromFunctionFromX( double xStart, double incrFactor,
                                                 Object helpObject, FunctionFromX functionFromX )
   {
      double x1, x2, xTest;
      double y1, y2, yTest;

      x1 = x2 = xTest = xStart;
      y1 = y2 = yTest = functionFromX.execute( xTest, helpObject );

      for( int i = 0; i < 1000000; i++ ) {
         xTest *= incrFactor;
         yTest = functionFromX.execute( xTest, helpObject );
         if( yTest < y1 ) {
            x1 = xTest;
            y1 = yTest;
            break;
         }
         x2 = x1;
         x1 = xTest;
         y2 = y1;
         y1 = yTest;
      }

      for( int i = 0; i < 1000000; i++ ) {
         xTest = (x1 + x2) / 2;
         yTest = functionFromX.execute( xTest, helpObject );
         if( y2 >= y1 ) {
            x1 = xTest;
            y1 = yTest;
         } else {
            x2 = xTest;
            y2 = yTest;
         }
         if( i > 10 && Math.abs( y2 - y1 ) < 1.0E-12 ) {
            break;
         }
      }

      return xTest;
   }

   private static double[] convertStringArrToDoubleArr( String[] strArr )
   {
      if( strArr == null || strArr.length <= 0 ) return null;

      double[] dblArr = new double[strArr.length];

      for( int i=0; i < strArr.length; i++ ) {
         strArr[i] = strArr[i].replace( ',', '.' );
         dblArr[i] = Double.parseDouble( strArr[i] );
      }

      return dblArr;
   }

   private static double roundSignificant( double d, int significantPrecision )
   {
      if( d == 0 || significantPrecision < 1 || significantPrecision > 14 ) return d;
      double mul10 = 1;
      double minVal = Math.pow( 10, significantPrecision - 1 );
      while( Math.abs( d ) < minVal ) {
         mul10 *= 10;
         d *= 10;
      }
      return Math.round( d ) / mul10;
   }

   private static String linksbuendigerString( String s, String fillStrWithWantLen )
   {
      if( s != null ) {
         int len = s.length();
         if( len < fillStrWithWantLen.length() ) {
            return (s + fillStrWithWantLen).substring( 0, fillStrWithWantLen.length() );
         }
      }
      return s;
   }

   static class RegressionResult
   {
      double a;
      double b;
      double rr;
      String titel;
      String formel;
      ApproxFunction approxFunction;
   }

   interface ApproxFunction
   {
      double execute( double a, double b, double x );
   }

   interface FunctionFromX
   {
      double execute( double x, Object helpObject );
   }
}