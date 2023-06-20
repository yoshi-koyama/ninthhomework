# 第9回〜13回あたりまでの講義内容反映課題

## 1.アプリケーション概要

* キャラクターデータベースに対してCRUD処理を全て揃えたREST API（完成済）
* アプリケーション概略図 （後に作成）
* Junitによるテストコード実装（とりあえずここ！）
* CIの実装（後に作成予定）
* 自動テスト実装（後に作成予定）

## 2.キャラクターがもつ情報

| 項目   | 型        | 備考         |
|------|----------|------------|
| ID   | int      | 自動採番、DB主キー |
| name | String   |
| 年齢   | Intenger |

## 3.各クラス実装メゾット・単体テスト内容

<details><summary>Mapperクラス</summary><div>

| Method                                      | function                                                                                | テスト確認事項                                         |
|---------------------------------------------|-----------------------------------------------------------------------------------------|-------------------------------------------------|
| List<Characters\> findAll()                 | 登録されている全てのデータを取得する<br>SELECT * FROM characters                                          | ・ 全てのデータの全項目を取得できること<br>・データがない時は空として返すこと       |
| List<Characters\> searchByAge(Integer age)  | 指定された年齢より年上のキャラクターのみ返す<br>SELECT * FROM characters WHERE age > #{age}                   | ・指定された年齢より上の年齢のキャラクターを返すこと<br>・対象データがない時は空で返すこと |
| Optional<Characters\> searchById(int id)    | 指定したIDの情報を返すこと <br> SELECT * FROM characters WHERE id = #{id}                           | ・指定したIDの情報を返す<br>・IDが存在しない時は空で返す                |
| void createCharacter(Characters characters) | 自動採番されたIDに対して入力データを登録する<br> INSERT INTO characters(name, age) VALUES(#{name}, #{age})   | ・入力されたデータが登録できること                               |
| void updateCharacter(Characters characters) | 指定されたIDデータを更新する<br> UPDATE characters SET name = #{name}, age = #{age} WHERE id = #{id} | ・指定されたIDの情報を更新できること<br> ・IDが存在しない時は何もしないこと      |
| void deleteCharacter(int id)                | 指定されたIDに含まれる情報を削除する<br>DELETE FROM characters WHERE id = #{id}                          | ・指定されたIDの情報が削除されること<br>・IDが存在しない時は何もしない         |          |

</div></details>

<details><summary>Serviceクラス</summary><div>

| Method                                                       | function                | テスト確認事項                                                                                |
|--------------------------------------------------------------|-------------------------|----------------------------------------------------------------------------------------|
| List<Characters> getCharacters()                             | 全キャラクター情報をListとして返す     | ・全てのキャラクターの全項目情報が返ってくること<br>・リストが空の時は空で返すこと                                            |
| List<Characters> findByAge(Integer age)                      | 指定された年齢より年上のキャラクターのみ返す  | ・指定された年齢より上の年齢のキャラクターを返すこと<br> ・対象データがない時は空で返すこと                                       |
| Characters findById(int id)                                  | 指定したIDの情報を返す            | ・指定したIDの情報を返すこと<br>・IDが存在しない時はNotFoundExceptionをスローすること　                               |
| Characters createCharacter(CreateForm createForm)            | 自動採番されたIDに対して入力データを登録する | ・自動採番されたIDに対して入力されたデータが登録できること                                                         |
| Characters updateCharacter(int id, String name, Integer age) | 指定されたIDデータを更新する         | ・指定されたIDデータが更新されること<br>・年齢だけ名前だけのデータでも更新されること<br> ・IDが存在しない時はNotFoundExceptionをスローすること |
| void deleteCharacter(int id)                                 | 指定されたIDデータを削除する         | ・指定されたIDの情報が削除されること<br> ・IDが存在しない時はNotFoundExceptionをスローすること                           |                                                                

</div></details>

<details><summary>Controllerクラス</summary><div>

＊全メゾットにおいてレスポンスコードが200であることも確認する

| Method                                                                                                                     | function                                 | テスト確認事項                                              |
|----------------------------------------------------------------------------------------------------------------------------|------------------------------------------|------------------------------------------------------|
| Characters findCharacterById(@PathVariable("id") int id)                                                                   | 指定したIDの情報を返す                             | ・指定されたIDの情報をかえすこと                                    |
| List<CharactersResponse> selectCharacters()                                                                                | IDなしの全件情報を返す                             | ・IDを含まない全件情報を返すこと                                    |
| List<Characters> findCharacterByAge(@RequestParam(name = "age", required = false) Integer age)                             | 指定された年齢より年上のデータを返す<br> ・指定がない場合は全件データを返す | ・指定された年齢より年上のデータを返すこと<br> ・指定がない場合は全件データを返すこと        |
| ResponseEntity<Map<String, String>> create(@RequestBody @Validated CreateForm createForm, UriComponentsBuilder uriBuilder) | 自動採番されたIDに対して入力データを登録する                  | ・自動採番されたIDに対して入力されたデータが登録できること<br> ・バリデーションが発動していること |
| ResponseEntity<Map<String, String>> update(@PathVariable("id") int id, @RequestBody UpdateForm updateForm)                 | 指定されたIDデータを更新する                          | ・指定されたIDデータが更新されること                                  |
| ResponseEntity<Map<String, String>> delete(@PathVariable("id") int id) {charactersService.deleteCharacter(id)              | 指定されたIDデータを削除する                          | ・指定されたIDの情報が削除されること                                  |

</div></details>

<details><summary>ExceptionHandlerクラス</summary><div>

| Method                                                                                                | function              | テスト確認事項                          |
|-------------------------------------------------------------------------------------------------------|-----------------------|----------------------------------|
| ResponseEntity<Map<String, String>> handlerNotFound(NotFoundException ex, HttpServletRequest request) | 指定しIDが存在しない時にレスポンスを返す | ・IDが存在しない時にエラー情報を返す(ステータスコード404) |

</div></details>

## 4.起動手順

1.自分のPCにリポジトリをgit cloneする  
`git clone https://github.com/kamide28/ninthhomework.git`  
<br>
2.Dockerを起動  
ターミナルで以下のコマンドを実行する  
`docker compose up`  
`docker compose exec db mysql -uroot -p`  
パスワードを聞かれたら`password`と入力する。
<br>  
3.プロジェクト実行  
src/main/java/com/example/ninthhomework/NinthhomeworkApplication.javaを開き実行する  
<br>
4.PostmanやCurなどでリクエストを送る  
URLの共通部分：http://localhost:8080
各操作に応じたHTTPメソッド、URL、リクエストボディの入力内容は5.API仕様参照
<br>  
5.結果を確認

## 5.API仕様

### Curlコマンドと統合テスト確認事項

| Request | Method/curlコマンド例                                                                                                                                                                                                                                                       | テスト確認事項                                              |
|---------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------|
| GET     | Characters findCharacterById(@PathVariable("id") int id) <br> `curl --location 'http://localhost:8080/characters/1'`                                                                                                                                                   | ・指定されたIDの情報をかえすこと<br>・IDが存在しない時はエラー情報を返すこと           |
| GET     | List<CharactersResponse> selectCharacters()  <br> `curl --location 'http://localhost:8080/characters-without-id'`                                                                                                                                                      | ・IDを含まない全件情報を返すこと                                    |
| GET     | List<Characters> findCharacterByAge(@RequestParam(name = "age", required = false) Integer age) <br> `curl --location 'http://localhost:8080/characters?age=23'`                                                                                                        | ・指定された年齢より年上のキャラクターデータを返すこと<br>・年齢指定がない場合は全件データを返すこと |
| POST    | ResponseEntity<Map<String, String>> create(@RequestBody @Validated CreateForm createForm, UriComponentsBuilder uriBuilder)<br> `curl --location 'http://localhost:8080/characters' \--header 'Content-Type: application/json' \--data '{"name" :"メイ","age" : 5}'`      | ・自動採番されたIDに対して入力されたデータが登録できること<br> ・バリデーションが発動していること |
| PACTH   | ResponseEntity<Map<String, String>> update(@PathVariable("id") int id, @RequestBody UpdateForm updateForm)  <br> `curl --location --request PATCH 'http://localhost:8080/characters/21' \--header 'Content-Type: application/json' \--data '{"name" :"メイ","age" : 4}'` | ・指定されたIDデータが更新されること <br> ・IDが存在しない時はエラー情報を返すこと       |
| DELETE  | ResponseEntity<Map<String, String>> delete(@PathVariable("id") int id) {charactersService.deleteCharacter(id) <br> `curl --location --request DELETE 'http://localhost:8080/characters/21'`                                                                            | ・指定されたIDの情報が削除されること<br> ・IDが存在しない時はエラー情報を返すこと        |


