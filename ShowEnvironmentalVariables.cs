/*
This page will show all environmental variables/connection strings/drives/AppSettings of the server/application.  Useful for debugging sometimes.
*/
protected void Page_Load(object sender, EventArgs e)
       {
           IDictionary variables = Environment.GetEnvironmentVariables();
           string[] drives = Environment.GetLogicalDrives();
           Response.Write("<strong>Environmentals</strong> <br>");
           foreach (DictionaryEntry var in variables)
           {
               Response.Write(var.Key + "~" + var.Value + "<br>");
           }
           Response.Write("<strong>Drives</strong> <br>");
           foreach (string drive in drives)
           {
               Response.Write(drive + "<br>");
           }
           Response.Write("<strong>AppSettings</strong> <br>");
           foreach (DictionaryEntry setting in ConfigurationManager.AppSettings)
           {
               Response.Write(setting.Key + "~" + setting.Value + "<br>");
           }
           Response.Write("<strong>ConnectionStrings</strong> <br>");
           foreach (ConnectionStringSettings setting in ConfigurationManager.ConnectionStrings)
           {
               Response.Write(setting.Name + "~" + setting.ConnectionString + "<br>");
           }
       }