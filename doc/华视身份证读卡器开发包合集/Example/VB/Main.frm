VERSION 5.00
Begin VB.Form Main 
   BorderStyle     =   1  'Fixed Single
   Caption         =   "���ӵ������֤�Ķ�����̬���������"
   ClientHeight    =   6690
   ClientLeft      =   45
   ClientTop       =   435
   ClientWidth     =   10665
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   6690
   ScaleWidth      =   10665
   StartUpPosition =   3  '����ȱʡ
   Begin VB.CommandButton Command9 
      Caption         =   "��ȡ֤��GetPeopleIDCode"
      Height          =   495
      Left            =   330
      TabIndex        =   31
      Top             =   4440
      Width           =   1695
   End
   Begin VB.CommandButton Command8 
      Caption         =   "��ȡ��ַGetPeopleAddress"
      Height          =   495
      Left            =   330
      TabIndex        =   30
      Top             =   5040
      Width           =   1695
   End
   Begin VB.CommandButton Command7 
      Caption         =   "��ȡ����GetPeopleName"
      Height          =   495
      Left            =   330
      TabIndex        =   29
      Top             =   3840
      Width           =   1695
   End
   Begin VB.Frame Frame4 
      Caption         =   "��Ƶ(������Ч)"
      ForeColor       =   &H00FF0000&
      Height          =   975
      Left            =   4200
      TabIndex        =   26
      Top             =   1200
      Width           =   1695
      Begin VB.OptionButton Option10 
         Caption         =   "�ر���Ƶ"
         Height          =   255
         Left            =   240
         TabIndex        =   28
         Top             =   600
         Width           =   1095
      End
      Begin VB.OptionButton Option9 
         Caption         =   "����Ƶ"
         Height          =   255
         Left            =   240
         TabIndex        =   27
         Top             =   240
         Value           =   -1  'True
         Width           =   1095
      End
   End
   Begin VB.Frame Frame3 
      Caption         =   "���ؽ��"
      Height          =   5085
      Left            =   6000
      TabIndex        =   18
      Top             =   1170
      Width           =   4395
      Begin VB.ListBox List1 
         Height          =   4380
         ItemData        =   "Main.frx":0000
         Left            =   120
         List            =   "Main.frx":0002
         TabIndex        =   19
         Top             =   240
         Width           =   4155
      End
      Begin VB.Label Label6 
         AutoSize        =   -1  'True
         Caption         =   "0 - ����ʧ��"
         ForeColor       =   &H000000FF&
         Height          =   180
         Left            =   1890
         TabIndex        =   23
         Top             =   4770
         Width           =   1080
      End
      Begin VB.Label Label5 
         AutoSize        =   -1  'True
         Caption         =   "1 - �����ɹ�"
         ForeColor       =   &H00FF0000&
         Height          =   180
         Left            =   420
         TabIndex        =   22
         Top             =   4770
         Width           =   1080
      End
   End
   Begin VB.Frame Frame2 
      Caption         =   "������Ϣѡ��"
      ForeColor       =   &H00FF0000&
      Height          =   2175
      Left            =   2130
      TabIndex        =   12
      Top             =   2190
      Width           =   3825
      Begin VB.OptionButton Option8 
         Caption         =   "6:���֣�ͼƬ�ļ����豸Ψһ���к�ǰ��λ����(�ն����绷��ʹ��)"
         ForeColor       =   &H00FF0000&
         Height          =   360
         Left            =   150
         TabIndex        =   24
         Top             =   1710
         Width           =   3225
      End
      Begin VB.OptionButton Option7 
         Caption         =   "5:оƬ�����IINSNDN.bin"
         Height          =   240
         Left            =   150
         TabIndex        =   17
         Top             =   1440
         Width           =   3465
      End
      Begin VB.OptionButton Option6 
         Caption         =   "4:����(����)wz.txt,ͼƬzp.bmp"
         ForeColor       =   &H00FF0000&
         Height          =   240
         Left            =   150
         TabIndex        =   16
         Top             =   1146
         Value           =   -1  'True
         Width           =   3465
      End
      Begin VB.OptionButton Option5 
         Caption         =   "3:����סַnewadd.txt"
         Height          =   240
         Left            =   150
         TabIndex        =   15
         Top             =   854
         Width           =   3465
      End
      Begin VB.OptionButton Option4 
         Caption         =   "2:����wz.txt,����xp.wlt"
         Height          =   240
         Left            =   150
         TabIndex        =   14
         Top             =   562
         Width           =   3465
      End
      Begin VB.OptionButton Option3 
         Caption         =   "1:����wz.txt,����xp.wlt,ͼƬzp.bmp"
         Height          =   240
         Left            =   150
         TabIndex        =   13
         Top             =   270
         Width           =   3465
      End
   End
   Begin VB.Frame Frame1 
      Caption         =   "ͨѸ��"
      ForeColor       =   &H00FF0000&
      Height          =   975
      Left            =   2130
      TabIndex        =   7
      Top             =   1170
      Width           =   1995
      Begin VB.ComboBox comU 
         Height          =   300
         Left            =   840
         TabIndex        =   11
         Text            =   "Combo3"
         Top             =   540
         Width           =   1005
      End
      Begin VB.ComboBox comS 
         Height          =   300
         Left            =   840
         TabIndex        =   10
         Text            =   "Combo3"
         Top             =   210
         Width           =   1005
      End
      Begin VB.OptionButton Option2 
         Caption         =   "USB"
         Height          =   225
         Left            =   150
         TabIndex        =   9
         Top             =   600
         Value           =   -1  'True
         Width           =   705
      End
      Begin VB.OptionButton Option1 
         Caption         =   "����"
         Height          =   255
         Left            =   150
         TabIndex        =   8
         Top             =   270
         Width           =   765
      End
   End
   Begin VB.CommandButton Command6 
      Caption         =   "�˳�"
      Height          =   405
      Left            =   4680
      TabIndex        =   6
      Top             =   5640
      Width           =   1215
   End
   Begin VB.CommandButton Command5 
      Caption         =   "���"
      Height          =   435
      Left            =   4680
      TabIndex        =   5
      Top             =   4920
      Width           =   1215
   End
   Begin VB.CommandButton Command4 
      Caption         =   "��ȡ��ϢCVR_Read_Content"
      Height          =   705
      Left            =   330
      TabIndex        =   4
      Top             =   3000
      Width           =   1695
   End
   Begin VB.CommandButton Command3 
      Caption         =   "���֤��֤CVR_Authenticate"
      Height          =   705
      Left            =   330
      TabIndex        =   3
      Top             =   2160
      Width           =   1695
   End
   Begin VB.CommandButton Command2 
      Caption         =   "�ر�����CVR_CloseComm"
      Height          =   705
      Left            =   330
      TabIndex        =   2
      Top             =   5640
      Width           =   1695
   End
   Begin VB.CommandButton Command1 
      Caption         =   "��������CVR_InitComm"
      Height          =   705
      Left            =   330
      TabIndex        =   1
      Top             =   1290
      Width           =   1695
   End
   Begin VB.Label Label4 
      Caption         =   "ע�⣺��ȷ����C��\Ŀ¼������Ƭ������Ȩ�ļ�Termb.lic ,���ļ�����Ϊ����"
      ForeColor       =   &H000000FF&
      Height          =   585
      Left            =   2160
      TabIndex        =   25
      Top             =   4560
      Width           =   2415
   End
   Begin VB.Label Label3 
      AutoSize        =   -1  'True
      Caption         =   "2006-04-14"
      Height          =   180
      Left            =   8400
      TabIndex        =   21
      Top             =   960
      Width           =   900
   End
   Begin VB.Image Image1 
      Height          =   1005
      Left            =   30
      Picture         =   "Main.frx":0004
      Top             =   60
      Width           =   2310
   End
   Begin VB.Label Label1 
      AutoSize        =   -1  'True
      BackStyle       =   0  'Transparent
      Caption         =   "����CVR-100ϵ�ж�̬�⿪���� V1.00"
      BeginProperty Font 
         Name            =   "����"
         Size            =   18
         Charset         =   134
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00FF0000&
      Height          =   360
      Left            =   2790
      TabIndex        =   0
      Top             =   330
      Width           =   6285
   End
   Begin VB.Label Label2 
      AutoSize        =   -1  'True
      BackStyle       =   0  'Transparent
      Caption         =   "����CVR-100ϵ�ж�̬�⿪���� V1.00"
      BeginProperty Font 
         Name            =   "����"
         Size            =   18
         Charset         =   134
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00FFFFFF&
      Height          =   360
      Left            =   2820
      TabIndex        =   20
      Top             =   360
      Width           =   6285
   End
End
Attribute VB_Name = "Main"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False

Private Sub Command1_Click()
    'CVR_InitComm
    If Option1.Value = True Then
        List1.AddItem "�����ӻ��ߡ� ���� " & comS.ListIndex + 1
        List1.AddItem "���� " & CVR_InitComm(comS.ListIndex + 1)
    Else
        List1.AddItem "�����ӻ��ߡ� USB�� " & comU.ListIndex + 1
        List1.AddItem " ���� " & CVR_InitComm(comU.ListIndex + 1001)
    End If
End Sub

Private Sub Command2_Click()
    'CVR_CloseComm
    List1.AddItem "���ر����ӡ�"
    List1.AddItem " ���� " & CVR_CloseComm()
End Sub

Private Sub Command3_Click()
    'CVR_Authenticate
    List1.AddItem "�����֤��֤��"
    List1.AddItem " ���� " & CVR_Authenticate()
End Sub

Private Sub Command4_Click()
    'CVR_Read_Content
    Dim mode As Integer
    If Option3.Value = True Then
        mode = 1
    ElseIf Option4.Value = True Then mode = 2
    ElseIf Option5.Value = True Then mode = 3
    ElseIf Option6.Value = True Then mode = 4
    ElseIf Option7.Value = True Then mode = 5
    ElseIf Option8.Value = True Then mode = 6
    End If
    List1.AddItem "����ȡ��Ϣ��"
    List1.AddItem " ���� " & CVR_Read_Content(mode)
End Sub

Private Sub Command5_Click()
    List1.Clear
End Sub
Private Sub Command6_Click()
    CVR_CloseComm
    End
End Sub

Private Sub Command7_Click()
    Dim strTemp As String
    Dim nReturnLen As Integer
    Dim nReturn As Integer
    
    strTemp = Space(255)
    nReturn = GetPeopleName(strTemp, nReturnLen)
    
    List1.AddItem "����ȡ������"
    List1.AddItem " ������ " & strTemp
    
End Sub

Private Sub Command8_Click()
    Dim strTemp As String
    Dim nReturnLen As Integer
    Dim nReturn As Integer
    
    strTemp = Space(255)
    nReturn = GetPeopleAddress(strTemp, nReturnLen)
    
    List1.AddItem "����ȡ��ַ��"
    List1.AddItem " ��ַ�� " & strTemp
End Sub

Private Sub Command9_Click()
    Dim strTemp As String
    Dim nReturnLen As Integer
    Dim nReturn As Integer
    
    strTemp = Space(255)
    nReturn = GetPeopleIDCode(strTemp, nReturnLen)
    
    List1.AddItem "����ȡ֤�š�"
    List1.AddItem " ֤�ţ� " & strTemp
End Sub




Private Sub Form_Load()
    Dim i As Integer
    comS.Clear
    comU.Clear
    For i = 1 To 16
        comS.AddItem "����" & i
        comU.AddItem "USB��" & i
    Next i
    comS.ListIndex = 0
    comU.ListIndex = 0
   
    '����C��\��Ŀ¼���Ƿ�����Ƭ��ѹ��Ȩ�ļ�Termb.lic
    'If Dir("C:\termb.lic") = "" Then
        'C:\��û����Ȩ�ļ�,������Ȩ�ļ�
    '    FileCopy App.Path + "\termb.lic", "C:\termb.lic"
    'End If
    'SetAttr "C:\termb.lic", vbNormal    '�����ļ�����
    'SetListBoxHorizenScroll (List1)
    Const LB_SETHORIZONTALEXTENT     As Long = &H194
    SendMessage List1.hwnd, LB_SETHORIZONTALEXTENT, 500, ByVal 0
    
End Sub

Private Sub Form_Unload(Cancel As Integer)
    CVR_CloseComm
End Sub

Private Sub Option10_Click()
    'MsgBox "�ر���Ƶ"
    List1.AddItem "���ر���Ƶ��"
    List1.AddItem " ���� " & CVR_Ant(0)
    
End Sub

Private Sub Option9_Click()
    'MsgBox "����Ƶ"
    List1.AddItem "������Ƶ��"
    List1.AddItem " ���� " & CVR_Ant(1)
End Sub
