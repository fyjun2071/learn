class Trie:
    def __init__(self):
        self.root = {}

    def insert(self, word):
        node = self.root
        for c in word:
            if c not in node:
                node[c] = {}
            node = node[c]
        node['#'] = True


class Solution(object):
    def findWords(self, board, words):
        """
        :type board: List[List[str]]
        :type words: List[str]
        :rtype: List[str]
        """
        node = Trie()
        for word in words:
            node.insert(word)
        node = node.root

        visited = [[0] * len(board[0]) for i in range(len(board))]
        result = []
        temp = ''
        row = len(board)
        col = len(board[0])
        for i in range(row):
            for j in range(col):
                if board[i][j] in node:
                    self.dfs(board, i, j, visited, node, temp, result)
        return result

    def dfs(self, board, i, j, visited, node, temp, result):
        row = len(board)
        col = len(board[0])

        if '#' in node and temp not in result:
            return result.append(temp)
        if i > row - 1 or i < 0 or j > col - 1 or j < 0:
            return
        if board[i][j] not in node or visited[i][j] == 1:
            return

        temp += board[i][j]
        visited[i][j] = 1
        self.dfs(board, i + 1, j, visited, node[board[i][j]], temp, result)
        self.dfs(board, i - 1, j, visited, node[board[i][j]], temp, result)
        self.dfs(board, i, j + 1, visited, node[board[i][j]], temp, result)
        self.dfs(board, i, j - 1, visited, node[board[i][j]], temp, result)
        visited[i][j] = 0


words = ["oath", "pea", "eat", "rain"]
board = [
    ['o', 'a', 'a', 'n'],
    ['e', 't', 'a', 'e'],
    ['i', 'h', 'k', 'r'],
    ['i', 'f', 'l', 'v']
]
print(Solution().findWords(board, words))
