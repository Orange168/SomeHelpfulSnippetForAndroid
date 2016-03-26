public class ByteUtils {

	private int size;
	private byte[] buffer;
	
	public byte[] getBuffer() {
		return buffer;
	}

	private final int kBufferSizeIncrease = 1024;
	private final int kDefaultBufferSize = 1024;

	public ByteUtils() {
		buffer = new byte[kDefaultBufferSize];
		size = 0;
	}

	public long getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ByteUtils append(byte[] buf, int length) {

		if (size + length > buffer.length) {
			buffer = Arrays.copyOf(buffer, buffer.length + kBufferSizeIncrease);
		}
		System.arraycopy(buf, 0, buffer, size, length);
		size += length;
		return this;
	}

	public void erase(int begin, int count) {
		if (begin + count > size) {
			//Log.i("erase begin + count > size",".");
			size = begin;
		} else {
			//Log.i("erase else",".");
			int startIndex = begin + count;
			System.arraycopy(buffer, startIndex, buffer, begin, size-startIndex);
			size -= count;
		}
	}
	
	public void clear()
	{
		buffer = new byte[kDefaultBufferSize];
		size = 0;
	}
	
//	public static void main(String[] args) {
//		ByteUtils bu = new ByteUtils();
//		byte[] buf = new byte[32];
//		Arrays.fill(buf, (byte)1);
//		bu.append(buf,buf.length);
//		buf = new byte[1024];
//		Arrays.fill(buf, (byte)2);
//		bu.append(buf,buf.length);
//		System.out.println(bu.getSize());
//		bu.erase(0, 30);
//		System.out.println(bu.getSize());
//		bu.erase(4, 50);
//		System.out.println(bu.getSize());
//		System.out.println(bu.getBuffer()[2]);
//		System.out.println(bu.getBuffer()[1]);
//	}
}
