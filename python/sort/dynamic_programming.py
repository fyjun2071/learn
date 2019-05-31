# 动态规划

arr = []

opt = []
# 递归方法
def rec_opt(arr, i):
    if i == 0:
        return arr[0]
    elif i == 1:
        return max(arr[0], arr[1])
    else:
        A = opt[i - 2] + opt[i]
        B = opt[i - 1]
        return max(A, B)

# 动态规划方法
def dp_opt(arr):
    opt[0] = arr[0]
    opt[1] = max(arr[0], arr[1])
    for i in range(2, len(arr)):
        A = opt[i - 2] + opt[i]
        B = opt[i - 1]
        opt[i] = max(A, B);
    return opt[len(arr) - 1]
