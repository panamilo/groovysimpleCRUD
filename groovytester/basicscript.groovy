import groovy.sql.Sql
//Connection to the database hosted on localhost. Simple DB about songs and artists.
def dbUrl = "jdbc:postgresql://localhost:5432/MusicDB"
def dbUser = "postgres"
def dbPassword = "123"
def dbDriver = "org.postgresql.Driver"

def sql = Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriver)
// Simple Validation from DB users using username and password. No registration function yet.
/*println "Enter your username: "
def clientusername = System.in.newReader().readLine()
boolean isvalid1= sql.firstRow("""select * from client where username=$clientusername""")
println "Enter your password: "
def clientpassword = System.in.newReader().readLine()
boolean isvalid2 = sql.firstRow("""select * from client where username=$clientusername and password=$clientpassword """)
while(!isvalid1 || !isvalid2){

    println("Wrong credentials. Please try again: ")
    println("Please type in your username: ")
    clientusername = System.in.newReader().readLine()
    println("Please type in your password: ")
    clientpassword = System.in.newReader().readLine()
    isvalid1= sql.firstRow("""select * from client where username=$clientusername""")
    isvalid2 = sql.firstRow("""select * from client where username=$clientusername and password=$clientpassword """)

}


*/
// CRUD in Console for songs table in DB
println "Welcome username. Please pick an option: "
println "1. View your playlist. " //Read functionality1
println "2. Add a song to your playlist. " //Insert functionality
println "3. Edit a song's title from your playlist. " //Update Functionality
println "4. Remove a song from your playlist. " //Delete functionality
println "5. View the details of a specific song. " //Select specific functionality
println "6. Logout. \n" //End Script functionality
println "Type the number for your prefered action: "
// Simple action validation.
    def input = System.in.newReader().readLine()
    while(!input.isInteger()){
        println("Not acceptable input format. Please try again: ")
        input = System.in.newReader().readLine()
    }
action1= input as Integer
while(action1<1 || action1>6) {
    println("Input not acceptable. Please try again: ")
    input = System.in.newReader().readLine()
    while(!input.isInteger()){
        println("Not acceptable input format. Please try again: ")
        input = System.in.newReader().readLine()
    }
    action1= input as Integer
}
    while (action1 != 6) {

        switch (action1) {
            case 1:
                //Select and print on console every column of song table.
                println("Songs: ")
                def rows=sql.rows("Select * from song")
                println rows.join('\n')
                break
            case 2:
                //insert data into song table.
                println("Please insert the values of the song as stated below. ")
                println("Please type the desired song's id.")
                def songid = System.in.newReader().readLine() as Integer
                boolean isvalidid=sql.firstRow("""select * from song where idsong=$songid""")
                while(!isvalidid){
                    println("The specified ID doesn't exist. Please try again: ")
                    songid = System.in.newReader().readLine() as Integer
                    isvalidid=sql.firstRow("""select * from song where idsong=$songid""")
                }
                println("Please type the desired song's title.")
                def songtitle= System.in.newReader().readLine()
                println("Please type the desired song's album")
                def songalbum= System.in.newReader().readLine()
                println("Please type the desired song's genre")
                def songgenre= System.in.newReader().readLine()
                println("Please type the desired song's artist's id.")
                def songartistid= System.in.newReader().readLine() as Integer
                sql.execute("insert into song (idsong,songname,album,genre,artist_id) values ($songid,$songtitle,$songalbum,$songgenre,$songartistid)")
                println("Song successfully inserted into the playlist.")
                break
            case 3:
                // Edit/update a songs title .
                println("Please select the id of the song's title you want to edit.")
                def desiredid=System.in.newReader().readLine() as Integer
                boolean isvalidid=sql.firstRow("""select * from song where idsong=$desiredid""")
                while(!isvalidid){
                    println("The id of the song you are trying to edit doesn't exist. Please try again: ")
                    desiredid=System.in.newReader().readLine() as Integer
                    isvalidid=sql.firstRow("""select * from song where idsong=$desiredid""")
                }
                println("Please choose the new title for the specific song: ")
                def desiredtitle=System.in.newReader().readLine()
                sql.execute("update song set songname=$desiredtitle where idsong=$desiredid")
                println("Song's title successfully updated.")
                break
            case 4:
                // delete an entry from song table
                println("Available Songs")
                def rows=sql.rows("Select * from song")
                println rows.join('\n')
                println("Please type the song's id you want to delete from the playlist. ")
                def deleteid= System.in.newReader().readLine() as Integer
                sql.execute("""delete from song where idsong=$deleteid""")
                println("Song successfully deleted from the playlist.")
                break
            case 5:
                //placeholder action.
                println("Please type in the id of your preferred song.")
                def desiredid=System.in.newReader().readLine() as Integer
                def getid=sql.firstRow("""select * from song where idsong=$desiredid""")
                println getid
                break


        }
        println("Type the number for your prefered action: ")
        input = System.in.newReader().readLine()
        while(!input.isInteger()){
            println("Not acceptable input format. Please try again: ")
            input = System.in.newReader().readLine()
        }
        action1= input as Integer
    }

println("Logout successful. See you next time! ")


