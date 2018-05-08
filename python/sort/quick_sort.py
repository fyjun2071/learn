# 快速排序
def quick_sort(array, l, r):
    if l < r:
        q = partition(array, l, r)
        quick_sort(array, l, q - 1)
        quick_sort(array, q + 1, r)


def partition(array, l, r):
    """按降序, 找到枢纽值的索引"""
    x = array[r]
    i = l - 1
    for j in range(l, r):
        if array[j] >= x:
            i += 1
            array[i], array[j] = array[j], array[i]
    array[i + 1], array[r] = array[r], array[i + 1]
    return i + 1


def search(array, l, r, k):
    """
    找出数组中第k大的数
    :param array:
    :param l:
    :param r:
    :param k:
    :return:
    """

    p = partition(array, l, r)
    if p < k:
        p = search(array, p + 1, r, k)
    elif p > k:
        p = search(array, l, p - 1, k)
    else:
        pass
    return p


def test_quick_sort():
    a = [0, 8, 9, 6, 5, 4, 3, 2, 1, 7]
    quick_sort(a, 0, len(a) - 1)
    print(a)


def test_search():
    a = [0, 8, 9, 6, 5, 4, 3, 2, 1, 7]
    k = 1
    n = search(a, 0, len(a) - 1, k - 1)
    print(a)
    print(a[n])


# test_quick_sort()
test_search()
