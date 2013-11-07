
/* espresso init script : start */
function init() {
	if(false)alert(arguments[0]!=null?arguments[0]:"No parameter!!" );
	var arg = arguments[0]!=null?arguments[0]:"";//// context path
espresso=new es.editor.Editor();
espresso.initParameter({'initial_parameter':{header_scripts:[],footer_scripts:[],links:[arg+'/css/content_css.css'],styles:["body {font-family:����,Dotum,San-serif;font-size:9pt;}"],contents:'',default_font:'{$default_font}'},'attach_list_setting':{resource_url:'http://c1img3.cyworld.co.kr/img/editor'},'attach_file_setting':{external_image_url:'http://c2img.cyworld.co.kr/img/ko/editor/default'},'attach_image_setting':{allowed_image_extension:'jpg,gif,png'}});
espresso.initEditor(
		{
			component:{
				WYSIWYGEditor:
					{
						cls:'es.component.WysiwygEditor'
						,path:'/component/WYSIWYGEditor'
						,command:[{cmd:'convertMoreLessViewToEditor'
						,target:'component.toolbar.plugin.MoreLessBtn'
					}
					,{cmd:'checkMoreLessControl',target:'component.toolbar.plugin.MoreLessBtn'}
					,{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'}]
					,plugin:['UndoRedo','Shortcut','FindReplace']}
					,toolbar:{cls:'es.component.Toolbar',path:'/component/toolbar_advanced',plugin:['ColorPicker','WebFontBtn','FontSizeBtn','FontStyleBtn','ColorBtn','AlignmentBtn','IndentBtn','ComplexListBtn','HrInsertBtn','EmoticonInsertBtn','LinkInsertBtn','UndoRedoBtn','SupSubScriptBtn','CSSUnderlineBtn','LineHeightBtn','QuoteInsertBtn','SymbolBtn','FindReplaceBtn','MoreLessBtn']},attach_toolbar:{cls:'es.component.AttachToolbar',path:'/component/attach_toolbar',command:[{cmd:'notifyUndo,notifyRedo,notifyUndoStackPush',target:'plugin.*'}],plugin:['AttachImage','AttachFile']},attach_list:{cls:'es.component.AttachList',path:'/component/attach_list',command:[{cmd:'setSize',target:'component.attach_size'},{cmd:'getDocument,setFocus,insertHTML,setModified',target:'component.WYSIWYGEditor'},{cmd:'deleteAttach,increaseAttachCnt',target:'component.attach_toolbar.plugin.*'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'},{cmd:'pushObject,undoObject,redoObject,setBaseObject',target:'filter.undoStack'}]},attach_size:{cls:'es.component.AttachSize',path:'/component/attach_size'}},plugin:{UndoRedo:{cls:'es.plugin.UndoRedo',path:'/plugin/undoredo',command:[{cmd:'getDocument',target:'component.WYSIWYGEditor'},{cmd:'getSelection',target:'component.WYSIWYGEditor'},{cmd:'push',target:'filter.undoStack'},{cmd:'debug',target:'filter.undoStack'},{cmd:'undo,redo',type:'hook'},{cmd:'notifyUndo,notifyRedo',target:'component.*'}]},Shortcut:{cls:'es.plugin.Shortcut',path:'/plugin/shortcut',init_arg:['\'WYSIWYG_shortcut\'']},FindReplace:{cls:'es.plugin.FindReplace',path:'/plugin/find_replace',command:[{cmd:'*',target:'component.WYSIWYGEditor'},{cmd:'setMsg',target:'component.toolbar.plugin.FindReplaceBtn'}]},ColorPicker:{cls:'es.plugin.ColorPicker',path:'/plugin/color_picker',command:[{cmd:'*',target:'component.WYSIWYGEditor'}]},WebFontBtn:{cls:'es.plugin.WebFontBtn',path:'/plugin/webfont',command:[{cmd:'*',target:'component.WYSIWYGEditor'}],init_arg:['\'\'','false'],filter:{undoStack:{cmd:'setRangeStyles',filter_cmd:'push',type:'out',pos:'after'}}},FontSizeBtn:{cls:'es.plugin.FontSize',path:'/plugin/font_size',command:[{cmd:'*',target:'component.WYSIWYGEditor'}],filter:{undoStack:{cmd:'execCommand,setRangeStyles',filter_cmd:'push',type:'out',pos:'after'}}},FontStyleBtn:{cls:'es.plugin.FontStyle',path:'/plugin/font_style',command:[{cmd:'execCommand',target:'component.WYSIWYGEditor'},{cmd:'setFocus',target:'component.WYSIWYGEditor'}],filter:{undoStack:{cmd:'execCommand',filter_cmd:'push',type:'out',pos:'after'}}},ColorBtn:{cls:'es.plugin.ColorBtn',path:'/plugin/fore_back_color_advanced',command:[{cmd:'*',target:'component.WYSIWYGEditor'},{cmd:'showHideColorDialog',target:'component.toolbar.plugin.ColorPicker'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'}]},AlignmentBtn:{cls:'es.plugin.Alignment',path:'/plugin/alignment',command:[{cmd:'execCommand',target:'component.WYSIWYGEditor'},{cmd:'setFocus',target:'component.WYSIWYGEditor'}],filter:{undoStack:{cmd:'execCommand',filter_cmd:'push',type:'out',pos:'after'}}},IndentBtn:{cls:'es.plugin.Indent',path:'/plugin/indent',command:[{cmd:'execCommand',target:'component.WYSIWYGEditor'},{cmd:'setFocus',target:'component.WYSIWYGEditor'}],filter:{undoStack:{cmd:'execCommand',filter_cmd:'push',type:'out',pos:'after'}}},ComplexListBtn:{cls:'es.plugin.ComplexListBtn',path:'/plugin/ol_ul_list',command:[{cmd:'*',target:'component.WYSIWYGEditor'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'}],init_arg:['{\'type\':\'ordered\'}']},HrInsertBtn:{cls:'es.plugin.HrInsertBtn',path:'/plugin/hr_insertion',command:[{cmd:'*',target:'component.WYSIWYGEditor'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'}]},EmoticonInsertBtn:{cls:'es.plugin.EmoticonInsertBtn',path:'/plugin/emoticon_insertion',command:[{cmd:'*',target:'component.WYSIWYGEditor'}],filter:{undoStack:{cmd:'insertHTML',filter_cmd:'push',type:'out',pos:'after'}}},LinkInsertBtn:{cls:'es.plugin.LinkInsertBtn',path:'/plugin/link_insertion',command:[{cmd:'*',target:'component.WYSIWYGEditor'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'}]},UndoRedoBtn:{cls:'es.plugin.UndoRedoBtn',path:'/plugin/undoredo',command:[{cmd:'*',target:'component.WYSIWYGEditor'}]},SupSubScriptBtn:{cls:'es.plugin.SupSubScript',path:'/plugin/sup_sub_script',command:[{cmd:'execCommand',target:'component.WYSIWYGEditor'},{cmd:'setFocus',target:'component.WYSIWYGEditor'}],filter:{undoStack:{cmd:'execCommand',filter_cmd:'push',type:'out',pos:'after'}}},CSSUnderlineBtn:{cls:'es.plugin.CSSUnderlineBtn',path:'/plugin/css_underline',command:[{cmd:'*',target:'component.WYSIWYGEditor'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'}]},LineHeightBtn:{cls:'es.plugin.LineHeightBtn',path:'/plugin/line_height',command:[{cmd:'*',target:'component.WYSIWYGEditor'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'}]},QuoteInsertBtn:{cls:'es.plugin.QuoteInsertBtn',path:'/plugin/quote_insertion',command:[{cmd:'execCommand',target:'component.WYSIWYGEditor'},{cmd:'getSelection',target:'component.WYSIWYGEditor'},{cmd:'getDocument',target:'component.WYSIWYGEditor'},{cmd:'setFocus',target:'component.WYSIWYGEditor'},{cmd:'clearContents',target:'component.WYSIWYGEditor'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'}]},SymbolBtn:{cls:'es.plugin.SymbolBtn',path:'/plugin/symbol_insertion',command:[{cmd:'insertNode',target:'component.WYSIWYGEditor'},{cmd:'insertHTML',target:'component.WYSIWYGEditor'},{cmd:'setFocus',target:'component.WYSIWYGEditor'},{cmd:'getWidthOfEditor',target:'component.WYSIWYGEditor'},{cmd:'getLeftPositionOfEditor',target:'component.WYSIWYGEditor'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'}]},FindReplaceBtn:{cls:'es.plugin.FindReplaceBtn',path:'/plugin/find_replace',command:[{cmd:'*',target:'component.WYSIWYGEditor'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'},{cmd:'find',target:'component.WYSIWYGEditor.plugin.FindReplace'},{cmd:'replace',target:'component.WYSIWYGEditor.plugin.FindReplace'},{cmd:'replaceAll',target:'component.WYSIWYGEditor.plugin.FindReplace'}]},MoreLessBtn:{cls:'es.plugin.MoreLessBtn',path:'/plugin/more_less',command:[{cmd:'*',target:'component.WYSIWYGEditor'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'}]},AttachImage:{cls:'es.plugin.AttachImage',path:'/plugin/attach_image',command:[{cmd:'*',target:'component.WYSIWYGEditor'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'},{cmd:'syncAttach,addImageAttach',target:'component.attach_list'}]
                    ,init_arg:['\'./popUploadForm.omp?file_gb=I&cursize={$cursize}\'','375','170']}
					,AttachFile:{
						cls:'es.plugin.AttachFile'
						,path:'/plugin/attach_file'
						,command:[{cmd:'*',target:'component.WYSIWYGEditor'},{cmd:'saveUndo',target:'filter.undoStack',target_cmd:'push'},{cmd:'syncAttach,addFileAttach',target:'component.attach_list'}],init_arg:['\'/file/uploadform/attach?file_gb=F&cursize={$cursize}\'','375','170','10']}},filter:{undoStack:{cls:'es.filter.UndoStack',path:'/filter/undoStack',command:[{cmd:'getContentsInitialized,getDocument,',target:'component.WYSIWYGEditor'},{cmd:'notifyUndoStackPush',target:'component.*'},{cmd:'syncAttach',target:'component.attach_list'},{cmd:'syncTableControl',target:'component.toolbar.plugin.TableBtn'}],init_arg:['20']}},ids:{WYSIWYGEditor:{WYSIWYGEditor:'espresso_WYSIWYGEditor_WYSIWYGEditor',resize_bar:'espresso_WYSIWYGEditor_resize_bar'},ColorPicker:{ColorDialog:'espresso_ColorPicker_ColorDialog',ColorPalette:'espresso_ColorPicker_ColorPalette',ColorSpectrum:'espresso_ColorPicker_ColorSpectrum',CloseSpectrumBtn:'espresso_ColorPicker_CloseSpectrumBtn',HuePalette:'espresso_ColorPicker_HuePalette',HuePointer:'espresso_ColorPicker_HuePointer',SVPalette:'espresso_ColorPicker_SVPalette',SVPointer:'espresso_ColorPicker_SVPointer',SelectedColor:'espresso_ColorPicker_SelectedColor',RGBInput:'espresso_ColorPicker_RGBInput',SelectColorInSpectrum:'espresso_ColorPicker_SelectColorInSpectrum'},WebFontBtn:{webfont:'espresso_WebFontBtn_webfont',webfontLayer:'espresso_WebFontBtn_webfontLayer',webfont_list:'espresso_WebFontBtn_webfont_list',webfont_list_separator:'espresso_WebFontBtn_webfont_list_separator',webfontLayer_0:'espresso_WebFontBtn_webfontLayer_0',webfontLayer_1:'espresso_WebFontBtn_webfontLayer_1',webfontLayer_2:'espresso_WebFontBtn_webfontLayer_2',webfontLayer_3:'espresso_WebFontBtn_webfontLayer_3',webfontLayer_4:'espresso_WebFontBtn_webfontLayer_4',webfontLayer_5:'espresso_WebFontBtn_webfontLayer_5',webfontLayer_6:'espresso_WebFontBtn_webfontLayer_6',webfontLayer_7:'espresso_WebFontBtn_webfontLayer_7'},FontSizeBtn:{fontSize:'espresso_FontSizeBtn_fontSize',fontSize_layer:'espresso_FontSizeBtn_fontSize_layer',fontSize_layer_0:'espresso_FontSizeBtn_fontSize_layer_0',fontSize_layer_1:'espresso_FontSizeBtn_fontSize_layer_1',fontSize_layer_2:'espresso_FontSizeBtn_fontSize_layer_2',fontSize_layer_3:'espresso_FontSizeBtn_fontSize_layer_3',fontSize_layer_4:'espresso_FontSizeBtn_fontSize_layer_4',fontSize_layer_5:'espresso_FontSizeBtn_fontSize_layer_5',fontSize_layer_6:'espresso_FontSizeBtn_fontSize_layer_6',fontSize_layer_7:'espresso_FontSizeBtn_fontSize_layer_7',fontSize_layer_8:'espresso_FontSizeBtn_fontSize_layer_8'},FontStyleBtn:{Bold:'espresso_FontStyleBtn_Bold',Italic:'espresso_FontStyleBtn_Italic',Underline:'espresso_FontStyleBtn_Underline',Strike:'espresso_FontStyleBtn_Strike'},ColorBtn:{ForeColorChoice:'espresso_ColorBtn_ForeColorChoice',ForeColor:'espresso_ColorBtn_ForeColor',BackColorChoice:'espresso_ColorBtn_BackColorChoice',BackColor:'espresso_ColorBtn_BackColor'},AlignmentBtn:{LeftAlign:'espresso_AlignmentBtn_LeftAlign',CenterAlign:'espresso_AlignmentBtn_CenterAlign',RightAlign:'espresso_AlignmentBtn_RightAlign'},IndentBtn:{Indent:'espresso_IndentBtn_Indent',Outdent:'espresso_IndentBtn_Outdent'},ComplexListBtn:{ComplexListBtn:'espresso_ComplexListBtn_ComplexListBtn',ComplexListDlg:'espresso_ComplexListBtn_ComplexListDlg',ol_list1:'espresso_ComplexListBtn_ol_list1',ol_list2:'espresso_ComplexListBtn_ol_list2',ol_list3:'espresso_ComplexListBtn_ol_list3',ol_list4:'espresso_ComplexListBtn_ol_list4',ol_list5:'espresso_ComplexListBtn_ol_list5',ul_list1:'espresso_ComplexListBtn_ul_list1',ul_list2:'espresso_ComplexListBtn_ul_list2',ul_list3:'espresso_ComplexListBtn_ul_list3',i_list1:'espresso_ComplexListBtn_i_list1',r_list1:'espresso_ComplexListBtn_r_list1'},HrInsertBtn:{HrInsert:'espresso_HrInsertBtn_HrInsert',HrInsertDlg:'espresso_HrInsertBtn_HrInsertDlg',HrInsertDlg_0:'espresso_HrInsertBtn_HrInsertDlg_0',HrInsertDlg_hr_0:'espresso_HrInsertBtn_HrInsertDlg_hr_0',HrInsertDlg_1:'espresso_HrInsertBtn_HrInsertDlg_1',HrInsertDlg_hr_1:'espresso_HrInsertBtn_HrInsertDlg_hr_1',HrInsertDlg_2:'espresso_HrInsertBtn_HrInsertDlg_2',HrInsertDlg_hr_2:'espresso_HrInsertBtn_HrInsertDlg_hr_2',HrInsertDlg_3:'espresso_HrInsertBtn_HrInsertDlg_3',HrInsertDlg_hr_3:'espresso_HrInsertBtn_HrInsertDlg_hr_3',HrInsertDlg_4:'espresso_HrInsertBtn_HrInsertDlg_4',HrInsertDlg_hr_4:'espresso_HrInsertBtn_HrInsertDlg_hr_4',HrInsertDlg_5:'espresso_HrInsertBtn_HrInsertDlg_5',HrInsertDlg_hr_5:'espresso_HrInsertBtn_HrInsertDlg_hr_5'},EmoticonInsertBtn:{EmoticonInsert:'espresso_EmoticonInsertBtn_EmoticonInsert',emoticonLayer:'espresso_EmoticonInsertBtn_emoticonLayer',emoticon_tab1:'espresso_EmoticonInsertBtn_emoticon_tab1',emoticon1:'espresso_EmoticonInsertBtn_emoticon1',emoticon_tab2:'espresso_EmoticonInsertBtn_emoticon_tab2',emoticon2:'espresso_EmoticonInsertBtn_emoticon2',emoticon_tab3:'espresso_EmoticonInsertBtn_emoticon_tab3',emoticon3:'espresso_EmoticonInsertBtn_emoticon3'},LinkInsertBtn:{LinkInsert:'espresso_LinkInsertBtn_LinkInsert',LinkDlg:'espresso_LinkInsertBtn_LinkDlg',link_dialog_address:'espresso_LinkInsertBtn_link_dialog_address',link_dialog_target:'espresso_LinkInsertBtn_link_dialog_target',link_dialog_msg:'espresso_LinkInsertBtn_link_dialog_msg',link_dialog_ok:'espresso_LinkInsertBtn_link_dialog_ok',link_dialog_cancel:'espresso_LinkInsertBtn_link_dialog_cancel'},UndoRedoBtn:{Redo:'espresso_UndoRedoBtn_Redo',Undo:'espresso_UndoRedoBtn_Undo'},SupSubScriptBtn:{SuperScript:'espresso_SupSubScriptBtn_SuperScript',SubScript:'espresso_SupSubScriptBtn_SubScript'},CSSUnderlineBtn:{CSSUnderline:'espresso_CSSUnderlineBtn_CSSUnderline',CSSUnderlineDlg:'espresso_CSSUnderlineBtn_CSSUnderlineDlg',underline_1:'espresso_CSSUnderlineBtn_underline_1',underline_hr_1:'espresso_CSSUnderlineBtn_underline_hr_1',underline_2:'espresso_CSSUnderlineBtn_underline_2',underline_hr_2:'espresso_CSSUnderlineBtn_underline_hr_2',underline_3:'espresso_CSSUnderlineBtn_underline_3',underline_hr_3:'espresso_CSSUnderlineBtn_underline_hr_3',underline_4:'espresso_CSSUnderlineBtn_underline_4',underline_hr_4:'espresso_CSSUnderlineBtn_underline_hr_4',underline_5:'espresso_CSSUnderlineBtn_underline_5',underline_hr_5:'espresso_CSSUnderlineBtn_underline_hr_5',underline_6:'espresso_CSSUnderlineBtn_underline_6',underline_hr_6:'espresso_CSSUnderlineBtn_underline_hr_6',underline_7:'espresso_CSSUnderlineBtn_underline_7',color_1:'espresso_CSSUnderlineBtn_color_1',color_2:'espresso_CSSUnderlineBtn_color_2',color_3:'espresso_CSSUnderlineBtn_color_3',color_4:'espresso_CSSUnderlineBtn_color_4',color_5:'espresso_CSSUnderlineBtn_color_5',color_6:'espresso_CSSUnderlineBtn_color_6',CSSUnderline_Ok:'espresso_CSSUnderlineBtn_CSSUnderline_Ok',CSSUnderline_Cancel:'espresso_CSSUnderlineBtn_CSSUnderline_Cancel'},LineHeightBtn:{LineHeight:'espresso_LineHeightBtn_LineHeight',LineHeight_layer:'espresso_LineHeightBtn_LineHeight_layer',LineHeight_layer_0:'espresso_LineHeightBtn_LineHeight_layer_0',LineHeight_layer_1:'espresso_LineHeightBtn_LineHeight_layer_1',LineHeight_layer_2:'espresso_LineHeightBtn_LineHeight_layer_2',LineHeight_layer_3:'espresso_LineHeightBtn_LineHeight_layer_3',LineHeight_layer_4:'espresso_LineHeightBtn_LineHeight_layer_4',LineHeight_layer_5:'espresso_LineHeightBtn_LineHeight_layer_5'},QuoteInsertBtn:{QuoteInsert:'espresso_QuoteInsertBtn_QuoteInsert',QuoteInsertsDlg:'espresso_QuoteInsertBtn_QuoteInsertsDlg'},SymbolBtn:{SymbolBtn:'espresso_SymbolBtn_SymbolBtn',SymbolLayer:'espresso_SymbolBtn_SymbolLayer',charset_tab1:'espresso_SymbolBtn_charset_tab1',charset1:'espresso_SymbolBtn_charset1',charset_tab2:'espresso_SymbolBtn_charset_tab2',charset2:'espresso_SymbolBtn_charset2',charset_tab3:'espresso_SymbolBtn_charset_tab3',charset3:'espresso_SymbolBtn_charset3',charset_tab4:'espresso_SymbolBtn_charset_tab4',charset4:'espresso_SymbolBtn_charset4',charset_tab5:'espresso_SymbolBtn_charset_tab5',charset5:'espresso_SymbolBtn_charset5',SymbolLayer_recent:'espresso_SymbolBtn_SymbolLayer_recent',SymbolLayer_selected_text:'espresso_SymbolBtn_SymbolLayer_selected_text',SymbolLayer_ok:'espresso_SymbolBtn_SymbolLayer_ok'},FindReplaceBtn:{FindReplaceBtn:'espresso_FindReplaceBtn_FindReplaceBtn',findReplaceDlg:'espresso_FindReplaceBtn_findReplaceDlg',movingBar:'espresso_FindReplaceBtn_movingBar',close:'espresso_FindReplaceBtn_close',msg:'espresso_FindReplaceBtn_msg',find:'espresso_FindReplaceBtn_find',replace:'espresso_FindReplaceBtn_replace',replaceAll:'espresso_FindReplaceBtn_replaceAll'},MoreLessBtn:{MoreLess:'espresso_MoreLessBtn_MoreLess'},toolbar:{Toolbar:'espresso_toolbar_Toolbar',DefaultToolbar:'espresso_toolbar_DefaultToolbar',ToolbarEnlarge:'espresso_toolbar_ToolbarEnlarge',AdvancedToolbar:'espresso_toolbar_AdvancedToolbar',ToolbarReduce:'espresso_toolbar_ToolbarReduce'},AttachImage:{attachBtn:'espresso_AttachImage_attachBtn'},AttachFile:{attachBtn:'espresso_AttachFile_attachBtn',exceedNotify:'espresso_AttachFile_exceedNotify'},attach_list:{attach_list_wrap:'espresso_attach_list_attach_list_wrap',image_list:'espresso_attach_list_image_list',file_list:'espresso_attach_list_file_list'},attach_size:{container:'espresso_attach_size_container',bar:'espresso_attach_size_bar',cur:'espresso_attach_size_cur',max:'espresso_attach_size_max'}}});
espresso.registShortcut({WYSIWYG_shortcut:[{key:"CTRL+Z",target:"toolbar.UndoRedoBtn",method:"onClickUndo"},{key:"CTRL+Y",target:"toolbar.UndoRedoBtn",method:"onClickRedo"},{key:"ESC",target:"WYSIWYGEditor",cmd:"closeDialogs"},{key:"CTRL+S",target:"WYSIWYGEditor",cmd:"save"},{key:"CTRL+B",target:"toolbar.FontStyleBtn",method:"onClickBold"},{key:"CTRL+U",target:"toolbar.FontStyleBtn",method:"onClickUnderline"},{key:"CTRL+I",target:"toolbar.FontStyleBtn",method:"onClickItalic"},{key:"CTRL+K",target:"toolbar.FontStyleBtn",method:"onClickStrike"}],dummy:[]});
espresso.sendCommand('WYSIWYGEditor','setTempContent','<p>&nbsp;</p>');

}
/* espresso init script : end */