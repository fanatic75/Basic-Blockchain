import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class NoobChain {

	public static ArrayList<Block> blockChain = new ArrayList<Block>();
	private static int difficulty = 5;
	public static void main(String[] args) {

		Block genesisBlock = new Block("Hi I am the genesis block", "0");
		blockChain.add(genesisBlock);
		blockChain.get(0).mineBlock(difficulty);
		System.out.println("Hash for block 1 : " + genesisBlock.hash);
		Block secondBlock = new Block("Yo im the second block", blockChain.get(blockChain.size()-1).hash);
		blockChain.add(secondBlock);
		blockChain.get(1).mineBlock(difficulty);
		System.out.println("Hash for block 2 : " + secondBlock.hash);
		Block thirdBlock = new Block("Hey im the third block", blockChain.get(blockChain.size()-1).hash);
		blockChain.add(thirdBlock);
		blockChain.get(2).mineBlock(difficulty);
		System.out.println("Hash for block 3 : " + thirdBlock.hash);

		
	
		String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
		System.out.println(blockChainJson);
		
		if(!isChainValid()) {
			System.out.println("Chain not valid");
		}else {
			System.out.println("Chain valid");
		}
		
	}
	
	
	
	
	public static boolean isChainValid() {
		 Block previousBlock;
		 Block currentBlock;
		String hashTarget = new String(new char[difficulty]).replace("\0", "0");
		for(int i = 1; i<blockChain.size();i++) {
			previousBlock = blockChain.get(i-1);
			currentBlock = blockChain.get(i);
			if(!currentBlock.hash.equals( currentBlock.calculateHash())) {
				System.out.println("Hashes of current block is not the same as calculated hash. Data Manipulation in blockChain");
				return false;
			}
			if(!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Current Block's previous hash does not match previous block's hash.");
				return false;
			}
			if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			
		}
		return true;
	}
	
}
