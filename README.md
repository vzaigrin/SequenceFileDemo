# SequenceFile Demo

Пример приложения для работы с SequenceFile

* **writer** записывает в файл в формате SequenceFile
* **reader** читает файл в формате SequenceFile

## Пример
### Запись 
java java -jar write/target/scala-2.13/write.jar result/output
### Чтение
java -jar read/target/scala-2.13/read.jar result/output