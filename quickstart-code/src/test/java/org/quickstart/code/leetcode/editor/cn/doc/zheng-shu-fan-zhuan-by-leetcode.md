#### 方法：弹出和推入数字 & 溢出前进行检查

**思路**

我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。

**算法**

反转整数的方法可以与反转字符串进行类比。

我们想重复“弹出” *x* 的最后一位数字，并将它“推入”到 ![\text{rev} ](./p__text{rev}_.png)  的后面。最后，![\text{rev} ](./p__text{rev}_.png)  将与 *x* 相反。

要在没有辅助堆栈 / 数组的帮助下 “弹出” 和 “推入” 数字，我们可以使用数学方法。

```cpp
//pop operation:
pop = x % 10;
x /= 10;

//push operation:
temp = rev * 10 + pop;
rev = temp;
```

但是，这种方法很危险，因为当 ![\text{temp}=\text{rev}\cdot10+\text{pop} ](./p__text{temp}_=_text{rev}_cdot_10_+_text{pop}_.png)  时会导致溢出。

幸运的是，事先检查这个语句是否会导致溢出很容易。

为了便于解释，我们假设 ![\text{rev} ](./p__text{rev}_.png)  是正数。

1. 如果 ![temp=\text{rev}\cdot10+\text{pop} ](./p__temp_=_text{rev}_cdot_10_+_text{pop}_.png)  导致溢出，那么一定有 ![\text{rev}\geq\frac{INTMAX}{10} ](./p__text{rev}_geq_frac{INTMAX}{10}_.png) 。
2. 如果 ![\text{rev}>\frac{INTMAX}{10} ](./p__text{rev}___frac{INTMAX}{10}_.png) ，那么 ![temp=\text{rev}\cdot10+\text{pop} ](./p__temp_=_text{rev}_cdot_10_+_text{pop}_.png)  一定会溢出。
3. 如果 ![\text{rev}==\frac{INTMAX}{10} ](./p__text{rev}_==_frac{INTMAX}{10}_.png) ，那么只要 ![\text{pop}>7 ](./p__text{pop}___7_.png) ，![temp=\text{rev}\cdot10+\text{pop} ](./p__temp_=_text{rev}_cdot_10_+_text{pop}_.png)  就会溢出。

当 ![\text{rev} ](./p__text{rev}_.png)  为负时可以应用类似的逻辑。

```cpp [gkVs3hFn-C++]
class Solution {
public:
    int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > INT_MAX/10 || (rev == INT_MAX / 10 && pop > 7)) return 0;
            if (rev < INT_MIN/10 || (rev == INT_MIN / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
};
```
```java [gkVs3hFn-Java]
class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
```


**复杂度分析**

* 时间复杂度：![O(\log(x)) ](./p__O_log_x___.png) ，*x* 中大约有 ![\log_{10}(x) ](./p__log_{10}_x__.png)  位数字。
* 空间复杂度：*O(1)*。