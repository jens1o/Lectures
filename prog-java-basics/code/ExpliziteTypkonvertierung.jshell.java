void println(Object o) { System.out.println(o); }
;
final int i = 128;
println(Integer.toBinaryString(i));
println((byte) i);
println(Integer.toBinaryString(-1));
println(Integer.toBinaryString(-128));
println("(byte) 256: " + ((byte) 256));
println("Integer.toBinaryString(256): " + Integer.toBinaryString(256));
println("Integer.numberOfTrailingZeros(256):" + Integer.numberOfTrailingZeros(256));