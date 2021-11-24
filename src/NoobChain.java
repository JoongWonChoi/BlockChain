import java.util.Date;
import java.security.MessageDigest;

class Block{
	
	public String hash;  //our digital signature
	public String previousHash;  //our previous signature
	public String data;
	private long timeStamp;
	
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime(); 
		this.hash = calculatedHash();
	}
	public String calculatedHash() {
		String calculatedHash = StringUtil.applySha256(
				previousHash + Long.toString(timeStamp) + data
				);
		return calculatedHash;
	}
}

class StringUtil{
	public static String applySha256(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			//applies sha 256 to our input.
			byte[] hash = md.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for(int i=0;i<hash.length;i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length()==1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}	
	}
}

public class NoobChain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Block genesisBlock = new Block("First Block Hash","0");
		System.out.println("Hash for block 1 : "+genesisBlock.hash);
		
		Block secondBlock = new Block("Second Block Hash",genesisBlock.hash);
		System.out.println("Hash for block 2 : "+secondBlock.hash);
		
		Block thirdBlock = new Block("Third Block Hash",secondBlock.hash);
		System.out.println("Hash for block 1 : "+thirdBlock.hash);

	}

}
