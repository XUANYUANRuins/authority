Attribute VB_Name = "Module1"
Public Declare Function CVR_InitComm Lib "termb.dll" (ByVal Port As Long) As Integer
Public Declare Function CVR_CloseComm Lib "termb.dll" () As Integer
Public Declare Function CVR_Authenticate Lib "termb.dll" () As Integer
Public Declare Function CVR_Read_Content Lib "termb.dll" (ByVal Active As Long) As Integer
Public Declare Function CVR_Ant Lib "termb.dll" (ByVal mode As Long) As Integer

Public Declare Function GetPeopleName Lib "termb.dll" (ByVal lpReturnedString As String, ByRef nReturnLen As Integer) As Integer
Public Declare Function GetPeopleAddress Lib "termb.dll" (ByVal lpReturnedString As String, ByRef nReturnLen As Integer) As Integer
Public Declare Function GetPeopleIDCode Lib "termb.dll" (ByVal lpReturnedString As String, ByRef nReturnLen As Integer) As Integer


Public Declare Function SendMessage Lib "user32" _
            Alias "SendMessageA" (ByVal hwnd As Long, _
            ByVal wMsg As Long, ByVal wParam As Long, _
            lParam As Any) As Long

