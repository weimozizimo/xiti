package solution.solution4;

class Solution2 {
    public static void main(String[] args) {
        int[] num1 = {1,2};
        int[] num2 = {3,4};
        System.out.println(findMedianSortedArrays(num1,num2));
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int j = m+n;
        int k  = (m+n)/2;

        int i1=0,i2=0;

        while(true){

            int p = k/2-1;

            //如果有一个方数组为空，偶数情况下，返回另一方k和k+1的平均值，奇数返回k
            if(i1==m-1){
                if(j%2!=0){
                    return nums2[k-1];
                }else {
                    return (nums2[k-1]+nums2[k])/2d;
                }

            }
            if(i2==n-1){
                if(j%2!=0){
                    return nums1[k-1];
                }else {
                    return (nums1[k-1]+nums1[k])/2d;
                }
            }

            //如果k==1 则判断两边那个大，奇数情况下返回小的那个，偶数情况下，小的那方+1然后在做一次比较找出第k+1小的数然后求平均
            if(k==1){
                double r1;
                double r2;
                if(nums1[i1]<nums2[i2]){
                    r1 = nums1[i1];
                    if(++i1>=m){
                        r2 = nums2[i2];
                    }else {
                        r2 = nums1[i1]<nums2[i2]?nums1[i1]:nums2[i2];
                    }
                }else {
                    r1 = nums2[i2];
                    if(++i2>=m){
                        r2 = nums1[i1];
                    }else {
                        r2 = nums2[i2]<nums1[i1]?nums2[i2]:nums1[i1];
                    }
                }
                if(j%2==0){
                    return r1;
                }else{
                    return (r2+r1)/2d;
                }
            }

            int i1c = i1+p;
            int i2c = i2+p;

            if(nums1[i1c]>=nums2[i2c]){
                i2 = i2c+1;
            }else{
                i1 = i1c+1;
            }

            //如果数组越界，则取最后一个
            if(m-i1-1<p){
                i1 = m-1;
            }
            if(n-i2-1<p){
                i2 = n-2;
            }

            k = k-k/2;

        }



    }
}
