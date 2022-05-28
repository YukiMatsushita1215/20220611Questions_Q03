import java.util.Arrays;

class Main {

  public static void main(String[] args) {
    // ランダムに並べられた重複のない整数の配列
    var array = new int[] { 5, 4, 6, 2, 1, 9, 8, 3, 7, 10 };
    // ソート実行
    var sortedArray = new Main().sort(array);
    // 結果出力
    Arrays.stream(sortedArray).forEach(System.out::println);
  }

  private int[] sort(int[] array) {
    // 要素が一つの場合はソートの必要がないので、そのまま返却
    if (array.length == 1) {
      return array;
    }

    // 配列の先頭を基準値とする
    var pivot = array[0];

    // ここから記述
    int start = 0; //探索の先頭
    int end = array.length; //探索の末端
	var check = false; //すべてソートできているかを調べる
	int count = 0; //ソートした回数を調べる
	int missCount = 0; //ソートができていない回数
	
	//ソートがすべて終わったかどうか判定する
	while(check != true) {
		//先頭と末端の検索がぶつかるまで繰り返す
		missCount = 0; //初期化
		
		//探索がぶつかるまで繰り返す
	    while(start <= end) {
    		//先頭が末端側より先に、基準値より大きい値が見つかった場合、
	    	if(array[start] > pivot) {
	    		//末端のほうから値を確認する
	    		for(int i=end; i>start; i--) {
	    			//基準値より値が小さかった場合
	    			if(array[i-1] < pivot) {
	    				end = i; //調べた場所を更新する
	    				//値を入れ替える
	    				var tmp = array[start];
	    				array[start] = array[i-1];
	    				array[i-1] = tmp;
	    				//探索先を更新
	    	    		start +=1;
	    	    		end -=1;
	    			}
	    		}
	    	}else if(array[end-1] < pivot){ //末端側が先頭より先に、基準値より小さい値が見つかった場合、
	    		//先頭のほうから値を確認する
	    		for(int i=start; i<end; i++) {
	    			//基準値より値が大きかった場合
	    			if(array[i] > pivot) {
	    				start = i;//調べた場所を更新する
	    				//値を入れ替える
	    				var tmp = array[i];
	    				array[i] = array[end-1];
	    				array[end-1] = tmp;
	    				//探索先を更新
	    				start += 1;
	    				end -= 1;
	    			}
	    		}
	    	}else { //求める値が見つからなかった場合、
	    		//探索先を更新
	    		start +=1;
	    		end -=1;
	    	}
	    }
	    
	    
	    
	    //完全にソートされたかを判断する
	    for(int i=1; i<array.length; i++) {
	    	if(array[i-1] > array[i]) {
	    		missCount += 1;
    		}
	    	if(missCount != 0) {
	    		check = false;
	    	}else {
	    		check = true;
	    	}
    	}
	    
	    count += 1; //ソート回数を増加させる
	    
	    //ソート回数を調べ、回数をもとに調べる範囲を求める
	    if(count % 2 == 1) { //偶数回目のソートの場合、
	    	start = 0;
	    	end = array.length/(2*(count/2 +1));
	    }else { //奇数回目のソートの時、
	    	start = (count/2-1) + array.length/2;
	    	end = array.length;
	    }
	}
    
	return array;
    // ここまで記述

  }
}