package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	if len(os.Args) < 2 {
		fmt.Println("Usage: wcTool <filename> [-l] [-w] [-c]")
		return
	}

	fileName := os.Args[1]
	countLines := containsFlag(os.Args, "-l")
	countWords := containsFlag(os.Args, "-w")
	countChars := containsFlag(os.Args, "-c")

	processFile(fileName, countLines, countWords, countChars)
}

func containsFlag(args []string, flag string) bool {
	for _, arg := range args {
		if arg == flag {
			return true
		}
	}
	return false
}

func processFile(fileName string, countLines bool, countWords bool, countChars bool) {
	file, err := os.Open(fileName)
	if err != nil {
		fmt.Println("Error: ", err)
		return
	}
	defer file.Close()

	reader := bufio.NewScanner(file)
	lines := 0
	words := 0
	chars := 0

	for reader.Scan() {
		lines++
		words += countWordsInLine(reader.Text())
		chars += len(reader.Text())
	}

	if countLines {
		fmt.Println("Lines: ", lines)
	}

	if countWords {
		fmt.Println("Words: ", words)
	}

	if countChars {
		fmt.Println("Chars: ", chars)
	}
}

func countWordsInLine(line string) int {
	words := strings.Fields(line)
	return len(words)
}
