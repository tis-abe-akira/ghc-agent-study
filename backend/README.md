nablarch-example-rest
===============

Nablarchを使ったRESTfulウェブサービスのExampleです。

## 実行手順

### 1.動作環境
実行環境に以下のソフトウェアがインストールされている事を前提とします。
* Java Version : 17
* Maven 3.9.9以降

以下は、本手順では事前準備不要です。

|ソフトウェア|説明|
|:---|:---|
|APサーバ|このアプリケーションはJetty 12を使用しています。jetty-ee10-maven-pluginはJetty 12へのアプリケーションのデプロイ、起動を行います。|
|DBサーバ|このアプリケーションはH2 Database Engine(以下H2)を組み込んであるため、別途インストールの必要はありません。|

### 2. プロジェクトリポジトリの取得
Gitを使用している場合、アプリケーションを配置したいディレクトリにて「git clone」コマンドを実行してください。
以下、コマンドの例です。

    $mkdir c:\example
    $cd c:\example
    $git clone https://github.com/nablarch/nablarch-example-rest.git

Gitを使用しない場合、最新のタグからzipをダウンロードし、任意のディレクトリへ展開してください。

### 3  アプリケーションのビルドと起動
jetty-ee10-maven-pluginを実行して、アプリケーションのビルドとJetty 12の起動を行います。以下のコマンドを実行してください。

    $cd nablarch-example-rest
    $mvn jetty:run

#### コマンドの補足
本Exampleアプリケーションを起動するまでに必要な手順は以下になります。

1. データベースのセットアップ及びエンティティクラスの作成
1. アプリケーションのビルド
1. Jetty 12の起動

これらの手順はJetty 12を起動する`jetty:run`を実行する際にすべて含まれていますが、個別に実行することもできます。

##### データベースのセットアップ及びエンティティクラスの作成

以下のコマンドを実行することでデータベースのセットアップ及びエンティティクラスの作成を行うことができます。
テーブルのデータを初期化する場合も、本コマンドを実行することで可能です。

    $mvn generate-resources

※gspプラグインをJava 17で実行するためにはJVMオプションの指定が必要ですが、そのオプションは`.mvn/jvm.config`で指定しています。

##### アプリケーションのビルド

以下のコマンドを実行することでアプリケーションのビルドを行うことができます。

    $mvn compile

※`compile`を実行するとデータベースのセットアップ及びエンティティクラスの作成処理も行われます。


### 5. テスト用クライアントクラスからのアクセス

`src\test\java`以下に配置している、テスト用クライアントクラスのmainメソッドを実行します。

* com.nablarch.example.client.ProjectClient

IDEから実行するか、別のターミナルを開いて以下のコマンドを実行してください。

    $cd c:\example\nablarch-example-rest
    $mvn test-compile
    $mvn exec:java -Dexec.mainClass=com.nablarch.example.client.ProjectClient -Dexec.classpathScope=test

初期状態のデータでは、標準出力に以下の内容が表示されればOKです。

```log
---- projects (size: 10) ----
Project({projectId=1, projectName=プロジェクト００１, projectType=development, projectClass=s, projectStartDate=2010/09/18, projectEndDate=2015/04/09, clientId=1, projectManager=鈴木, projectLeader=佐藤, userId=100, note=備考欄, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=2, projectName=プロジェクト００２, projectType=maintenance, projectClass=b, projectStartDate=2010/09/18, projectEndDate=2014/11/10, clientId=2, projectManager=佐藤, projectLeader=鈴木, userId=100, note=null, sales=null, costOfGoodsSold=null, sga=null, allocationOfCorpExpenses=null, client=null, systemAccount=null})
Project({projectId=3, projectName=プロジェクト００３, projectType=development, projectClass=c, projectStartDate=2015/04/09, projectEndDate=2015/04/09, clientId=3, projectManager=田中, projectLeader=佐藤, userId=100, note=空白, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=4, projectName=プロジェクト００４, projectType=development, projectClass=a, projectStartDate=2012/06/22, projectEndDate=2013/04/01, clientId=4, projectManager=山田, projectLeader=田中, userId=100, note=なし, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=5, projectName=プロジェクト００５, projectType=maintenance, projectClass=ss, projectStartDate=2012/12/01, projectEndDate=2014/12/31, clientId=5, projectManager=鈴木, projectLeader=山田, userId=100, note=テスト, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=6, projectName=プロジェクト００６, projectType=maintenance, projectClass=d, projectStartDate=2012/06/22, projectEndDate=2015/01/08, clientId=6, projectManager=佐藤, projectLeader=鈴木, userId=100, note=null, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=7, projectName=プロジェクト００７, projectType=development, projectClass=s, projectStartDate=2012/12/01, projectEndDate=2015/04/09, clientId=7, projectManager=鈴木, projectLeader=佐藤, userId=100, note=備考欄１, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=8, projectName=プロジェクト００８, projectType=development, projectClass=s, projectStartDate=2010/09/18, projectEndDate=2014/11/10, clientId=8, projectManager=佐藤, projectLeader=佐藤, userId=100, note=備考欄２, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=9, projectName=プロジェクト００９, projectType=development, projectClass=b, projectStartDate=2015/04/09, projectEndDate=2015/04/09, clientId=9, projectManager=田中, projectLeader=鈴木, userId=100, note=備考欄３, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=10, projectName=プロジェクト０１０, projectType=maintenance, projectClass=c, projectStartDate=2012/06/22, projectEndDate=2013/04/01, clientId=10, projectManager=山田, projectLeader=佐藤, userId=100, note=備考欄４, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
---- projects (size: 1) ----
Project({projectId=1, projectName=プロジェクト００１, projectType=development, projectClass=s, projectStartDate=2010/09/18, projectEndDate=2015/04/09, clientId=1, projectManager=鈴木, projectLeader=佐藤, userId=100, note=備考欄, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
insert status:201
---- projects (size: 11) ----
Project({projectId=1, projectName=プロジェクト００１, projectType=development, projectClass=s, projectStartDate=2010/09/18, projectEndDate=2015/04/09, clientId=1, projectManager=鈴木, projectLeader=佐藤, userId=100, note=備考欄, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=2, projectName=プロジェクト００２, projectType=maintenance, projectClass=b, projectStartDate=2010/09/18, projectEndDate=2014/11/10, clientId=2, projectManager=佐藤, projectLeader=鈴木, userId=100, note=null, sales=null, costOfGoodsSold=null, sga=null, allocationOfCorpExpenses=null, client=null, systemAccount=null})
Project({projectId=3, projectName=プロジェクト００３, projectType=development, projectClass=c, projectStartDate=2015/04/09, projectEndDate=2015/04/09, clientId=3, projectManager=田中, projectLeader=佐藤, userId=100, note=空白, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=4, projectName=プロジェクト００４, projectType=development, projectClass=a, projectStartDate=2012/06/22, projectEndDate=2013/04/01, clientId=4, projectManager=山田, projectLeader=田中, userId=100, note=なし, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=5, projectName=プロジェクト００５, projectType=maintenance, projectClass=ss, projectStartDate=2012/12/01, projectEndDate=2014/12/31, clientId=5, projectManager=鈴木, projectLeader=山田, userId=100, note=テスト, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=6, projectName=プロジェクト００６, projectType=maintenance, projectClass=d, projectStartDate=2012/06/22, projectEndDate=2015/01/08, clientId=6, projectManager=佐藤, projectLeader=鈴木, userId=100, note=null, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=7, projectName=プロジェクト００７, projectType=development, projectClass=s, projectStartDate=2012/12/01, projectEndDate=2015/04/09, clientId=7, projectManager=鈴木, projectLeader=佐藤, userId=100, note=備考欄１, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=8, projectName=プロジェクト００８, projectType=development, projectClass=s, projectStartDate=2010/09/18, projectEndDate=2014/11/10, clientId=8, projectManager=佐藤, projectLeader=佐藤, userId=100, note=備考欄２, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=9, projectName=プロジェクト００９, projectType=development, projectClass=b, projectStartDate=2015/04/09, projectEndDate=2015/04/09, clientId=9, projectManager=田中, projectLeader=鈴木, userId=100, note=備考欄３, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=10, projectName=プロジェクト０１０, projectType=maintenance, projectClass=c, projectStartDate=2012/06/22, projectEndDate=2013/04/01, clientId=10, projectManager=山田, projectLeader=佐藤, userId=100, note=備考欄４, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=11, projectName=プロジェクト９９９, projectType=development, projectClass=s, projectStartDate=2016/01/01, projectEndDate=2016/03/31, clientId=10, projectManager=鈴木, projectLeader=佐藤, userId=null, note=備考９９９, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
2023-03-24 18:07:49.742 -INFO- null [null] boot_proc = [] proc_sys = [rest] req_id = [null] usr_id = [null] initialized.
    LOGGER = [DEV] NAME REGEX = [DEV] LEVEL = [INFO]
    LOGGER = [PER] NAME REGEX = [PERFORMANCE] LEVEL = [INFO]
    LOGGER = [SQL] NAME REGEX = [SQL] LEVEL = [INFO]
    LOGGER = [ACC] NAME REGEX = [HTTP_ACCESS] LEVEL = [INFO]
    LOGGER = [ROO] NAME REGEX = [.*] LEVEL = [DEBUG]
update status:200
---- projects (size: 11) ----
Project({projectId=1, projectName=プロジェクト００１, projectType=development, projectClass=s, projectStartDate=2010/09/18, projectEndDate=2015/04/09, clientId=1, projectManager=鈴木, projectLeader=佐藤, userId=100, note=備考欄, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=2, projectName=プロジェクト００２, projectType=maintenance, projectClass=b, projectStartDate=2010/09/18, projectEndDate=2014/11/10, clientId=2, projectManager=佐藤, projectLeader=鈴木, userId=100, note=null, sales=null, costOfGoodsSold=null, sga=null, allocationOfCorpExpenses=null, client=null, systemAccount=null})
Project({projectId=3, projectName=プロジェクト００３, projectType=development, projectClass=c, projectStartDate=2015/04/09, projectEndDate=2015/04/09, clientId=3, projectManager=田中, projectLeader=佐藤, userId=100, note=空白, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=4, projectName=プロジェクト００４, projectType=development, projectClass=a, projectStartDate=2012/06/22, projectEndDate=2013/04/01, clientId=4, projectManager=山田, projectLeader=田中, userId=100, note=なし, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=5, projectName=プロジェクト００５, projectType=maintenance, projectClass=ss, projectStartDate=2012/12/01, projectEndDate=2014/12/31, clientId=5, projectManager=鈴木, projectLeader=山田, userId=100, note=テスト, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=6, projectName=プロジェクト００６, projectType=maintenance, projectClass=d, projectStartDate=2012/06/22, projectEndDate=2015/01/08, clientId=6, projectManager=佐藤, projectLeader=鈴木, userId=100, note=null, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=7, projectName=プロジェクト００７, projectType=development, projectClass=s, projectStartDate=2012/12/01, projectEndDate=2015/04/09, clientId=7, projectManager=鈴木, projectLeader=佐藤, userId=100, note=備考欄１, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=8, projectName=プロジェクト００８, projectType=development, projectClass=s, projectStartDate=2010/09/18, projectEndDate=2014/11/10, clientId=8, projectManager=佐藤, projectLeader=佐藤, userId=100, note=備考欄２, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=9, projectName=プロジェクト００９, projectType=development, projectClass=b, projectStartDate=2015/04/09, projectEndDate=2015/04/09, clientId=9, projectManager=田中, projectLeader=鈴木, userId=100, note=備考欄３, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=10, projectName=プロジェクト０１０, projectType=maintenance, projectClass=c, projectStartDate=2012/06/22, projectEndDate=2013/04/01, clientId=10, projectManager=山田, projectLeader=佐藤, userId=100, note=備考欄４, sales=10000, costOfGoodsSold=1000, sga=2000, allocationOfCorpExpenses=3000, client=null, systemAccount=null})
Project({projectId=11, projectName=プロジェクト８８８, projectType=development, projectClass=a, projectStartDate=2015/01/01, projectEndDate=2015/03/31, clientId=1, projectManager=佐藤, projectLeader=鈴木, userId=null, note=備考８８８, sales=20000, costOfGoodsSold=2000, sga=3000, allocationOfCorpExpenses=4000, client=null, systemAccount=null})

Process finished with exit code 0
```

### 6. DBの確認方法

1. https://www.h2database.com/html/download.html からH2をインストールしてください。  

2. {インストールフォルダ}/bin/h2.bat を実行してください(コマンドプロンプトが開く)。  
  ※h2.bat実行中はExampleアプリケーションからDBへアクセスすることができないため、Exampleアプリケーションを停止しておいてください。

3. ブラウザから http://localhost:8082 を開き、以下の情報でH2コンソールにログインしてください。
x   JDBC URLの{dbファイルのパス}には、`rest_example.mv.db`ファイルの格納ディレクトリまでのパスを指定してください。  
  JDBC URL：jdbc:h2:{dbファイルのパス}/rest_example  
  ユーザ名：REST_EXAMPLE  
  パスワード：REST_EXAMPLE
