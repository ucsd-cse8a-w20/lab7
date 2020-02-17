import java.time.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

class CSE8ALib {

  public static int square(int n) {
    return n*n;
  }

  public static int sign(int n) {
    if(n > 0) { return 1; }
    if(n < 0) { return -1; }
    return 0;
  }

  public static int roundTo(int n, int toRoundTo) {
    final int magRoundTo = Math.abs(toRoundTo);
    final int rem = Math.abs(n) % magRoundTo;
    final int multiple = Math.abs(n)/ magRoundTo;
    final int sign = n>=0?1:-1;
    if ( rem > (magRoundTo/2) ) {
      return (magRoundTo*(multiple+1)*sign);
    }
    else {
      return (magRoundTo*multiple*sign);
    }
  }

  public static int length(String s) {
    return s.length();
  }

  public static String substring(String base, int beginIndex, int endIndex) {
    return base.substring(beginIndex, endIndex);
  }

  public static String concat(String left, String right) {
    return left + right;
  }

  public static int indexOf(String base, String toFind) {
    return base.indexOf(toFind);
  }

  public static int indexOfNth(String base, String toFind, int n) {
    final int lenToFind = length(toFind);
    int baseIndex = 0;
    String baseSuffix = base;
    for (int toFindCounter=0; toFindCounter<=n; toFindCounter++) {
      int currIndex= indexOf(baseSuffix,toFind);
      if (currIndex == -1) {
        return -1;
      }
      else {
        baseIndex += currIndex;
        baseSuffix = substring(baseSuffix,currIndex+lenToFind,length(baseSuffix));
      }
    }
    return baseIndex + n * lenToFind;
  }

  public static int parseInt(String s) {
    return Integer.parseInt(s);
  }
  public static double parseDouble(String s) {
    return Double.parseDouble(s);
  }
  public static long parseLong(String s) {
    return Long.parseLong(s);
  }

  public static String readLine(String path, int index) {
    try {
      final Path filePath = Paths.get(path);
      final List<String> contents = Files.readAllLines(filePath);
      return contents.get(index);
    }
    catch (IOException ioe) {
        throw new RuntimeException(ioe.toString());
    }
  }

  public static int count(String base, String toFind) {
    if (toFind.isEmpty()) {
      throw new java.lang.IllegalArgumentException("toFind argument of count method must be nonempty string");
    }
    int count = 0;
    String suffix = base;
    while (!suffix.isEmpty()) {
      int indexToFind = indexOf(suffix,toFind);
      if(indexToFind == -1) {
        return count;
      }
      else {
        count += 1;
        suffix = substring(suffix, indexToFind+1, length(suffix));
      }
    }
    return count;
  }

  public static long dateMonthDayYearTime(String date) {
    try {
      final DateFormat d = new SimpleDateFormat("MMMMM dd yyyy hh:mm z");
      return (long)d.parse(date).getTime();
    } catch(java.text.ParseException e) {
      throw new IllegalArgumentException(e.toString());
    }
  }

  public static long dateYearMonthDayTime(String date) {
    try {
      final DateFormat d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      return (long)d.parse(date).getTime();
    } catch(java.text.ParseException e) {
      throw new IllegalArgumentException(e.toString());
    }
  }

  public static boolean show(String s, int numLines) {
    final String[] lines = s.split("\n");
    for(int i = 0; i < numLines; i += 1) {
      if(numLines >= lines.length) { return false; }
      System.out.println(lines[i]);
    }
    return true;
  }

  public static String readFile(String path) {
    try {
      final Path filePath = Paths.get(path);
      final List<String> contents = Files.readAllLines(filePath);
      StringBuilder result = new StringBuilder();
      for(String s: contents) { result.append(s + "\n"); }
      return result.toString();
    }
    catch (IOException ioe) {
      throw new RuntimeException(ioe.toString());
    }
  }

  public static String intToString(int n) {
    return Integer.toString(n);
  }

  public static double intToDouble(int n) {
    return (double)n;
  }

  public static String longToString(long l) {
    return Long.toString(l);
  }

  public static int doubleToInt(double d) {
    return (int)d;
  }


  public static String doubleToString(double d) {
    return Double.toString(d);
  }

  public static String trim(String s) {
    return s.trim();
  }

  public static boolean stringEquals(String s1, String s2) {
    return s1.equals(s2);
  }

  public static double sin(double n) {
    return Math.sin(n);
  }

  public static double cos(double n) {
    return Math.cos(n);
  }

  public static double tan(double n) {
    return Math.tan(n);
  }

  public static double sqrt(double n) {
    return Math.sqrt(n);
  }

  public static double pow(double base, double expt) {
    return Math.pow(base, expt);
  }

  public static int numColumns(String header) {
    return count(header,",")+1;
  }

  //Note: throws an exception if colName does not appear in headerRow
  public static int indexForColumn(String headerRow, String colName) {
    return count(substring(headerRow,0,indexOf(headerRow,colName)),",");
  }

  public static String commafy (String s) {
    return concat(",",concat(s,","));
  }

  public static String dataAtProcColumn(String dataProcRow, int col) {
    return substring(dataProcRow,indexOfNth(dataProcRow,",",col)+1,indexOfNth(dataProcRow,",",col+1));
  }

  public static String dataAtColumn(String dataRow, int col) {
    return dataAtProcColumn(commafy(dataRow),col);
  }

  public static int intAtColumn(String dataRow, int col) {
    return parseInt(dataAtProcColumn(commafy(dataRow),col));
  }

  public static double doubleAtColumn(String dataRow, int col) {
    return parseDouble(dataAtProcColumn(commafy(dataRow),col));
  }

  public static String newLinefy (String t) {
    if (length(t)>0 && !stringEquals(substring(t,length(t)-1,length(t)),"\n")) {
      return concat("\n",concat(t,"\n"));
    }
    else {
      return t;
    }
  }

  public static int numRows(String csv) {
    return count(newLinefy(csv),"\n");
  }

  public static String getRow(String t, int row) {
    return substring(newLinefy(t), indexOfNth(newLinefy(t),"\n",row)+1, indexOfNth(newLinefy(t),"\n",row+1));
  }

  public static String abridge(String csv, int numLines) {
    String[] rows = csv.split("\n");
    if (numLines > rows.length) return csv;
    StringBuilder newCSV = new StringBuilder();
    for(int i = 0; i < numLines+1; i++) {  //don't count header row
      newCSV.append(rows[i]+"\n");
    }
    return newCSV.toString();
  }

  public static double sumDouble(String csv, int col) {
    double sum = 0.0;
    boolean onFirst = true;
    for(String row: csv.split("\n")) {
      if(onFirst) {
        onFirst = false;
        continue;
      }
      sum += doubleAtColumn(row,col);
    }
    return sum;
  }

  public static double sumInt(String csv, int col) {
    double sum = 0.0;
    boolean onFirst = true;
    for(String row: csv.split("\n")) {
      if(onFirst) {
        onFirst = false;
        continue;
      }
      sum += intAtColumn(row,col);
    }
    return sum;
  }

  public static double meanDouble(String csv, int col) {
    return sumDouble(csv,col) / (numRows(csv)-1);
  }

  public static double meanInt(String csv, int col) {
    return sumInt(csv,col) / (numRows(csv)-1);
  }

  public static double medianDouble(String csv, int col) {
    String[] rows = substring(csv,indexOf(csv,"\n")+1,length(csv)).split("\n");
    double[] vals = new double[rows.length];
    for (int i = 0; i<rows.length; i++) {
      vals[i] = doubleAtColumn(rows[i],col);
    }
    Arrays.sort(vals);
    return vals[(vals.length-1)/2];
  }

  public static int medianInt(String csv, int col) {
    String[] rows = substring(csv,indexOf(csv,"\n")+1,length(csv)).split("\n");
    int[] vals = new int[rows.length];
    for (int i = 0; i<rows.length; i++) {
      vals[i] = intAtColumn(rows[i],col);
    }
    Arrays.sort(vals);
    return vals[(vals.length-1)/2];
  }


  public static double maxDouble(String csv, int col) {
    String[] rows = substring(csv,indexOf(csv,"\n")+1,length(csv)).split("\n");
    double[] vals = new double[rows.length];
    for (int i = 0; i<rows.length; i++) {
      vals[i] = doubleAtColumn(rows[i],col);
    }
    Arrays.sort(vals);
    return vals[vals.length-1];
  }

  public static int maxInt(String csv, int col) {
    String[] rows = substring(csv,indexOf(csv,"\n")+1,length(csv)).split("\n");
    int[] vals = new int[rows.length];
    for (int i = 0; i<rows.length; i++) {
      vals[i] = intAtColumn(rows[i],col);
    }
    Arrays.sort(vals);
    return vals[vals.length-1];
  }

  public static double minDouble(String csv, int col) {
    String[] rows = substring(csv,indexOf(csv,"\n")+1,length(csv)).split("\n");
    double[] vals = new double[rows.length];
    for (int i = 0; i<rows.length; i++) {
      vals[i] = doubleAtColumn(rows[i],col);
    }
    Arrays.sort(vals);
    return vals[0];
  }

  public static int minInt(String csv, int col) {
    String[] rows = substring(csv,indexOf(csv,"\n")+1,length(csv)).split("\n");
    int[] vals = new int[rows.length];
    for (int i = 0; i<rows.length; i++) {
      vals[i] = intAtColumn(rows[i],col);
    }
    Arrays.sort(vals);
    return vals[0];
  }

}
