FasdUAS 1.101.10   ��   ��    k             l     ��  ��    T N Converts an HTML file to PDF using "Safari's" "Save to PDF..." Functionality.     � 	 	 �   C o n v e r t s   a n   H T M L   f i l e   t o   P D F   u s i n g   " S a f a r i ' s "   " S a v e   t o   P D F . . . "   F u n c t i o n a l i t y .   
  
 l     ��  ��    A ; Reads from the command line the path of the HTML document.     �   v   R e a d s   f r o m   t h e   c o m m a n d   l i n e   t h e   p a t h   o f   t h e   H T M L   d o c u m e n t .      l     ��������  ��  ��        l     ��  ��    _ Y Example: 	osascript GeneratePDFs.scpt ~/Sites/delors.github.io/lab-shell/folien.rst.html     �   �   E x a m p l e :   	 o s a s c r i p t   G e n e r a t e P D F s . s c p t   ~ / S i t e s / d e l o r s . g i t h u b . i o / l a b - s h e l l / f o l i e n . r s t . h t m l      l     ��������  ��  ��        l     ��  ��    N H Remark: 		Safari's "Export as PDF" generates PDFs which does not honor      �   �   R e m a r k :   	 	 S a f a r i ' s   " E x p o r t   a s   P D F "   g e n e r a t e s   P D F s   w h i c h   d o e s   n o t   h o n o r        l     ��   ��    1 + 				media settings and we don't want that!      � ! ! V   	 	 	 	 m e d i a   s e t t i n g s   a n d   w e   d o n ' t   w a n t   t h a t !   " # " l     ��������  ��  ��   #  $ % $ l     �� & '��   & * $ Helpful URLs (for the development):    ' � ( ( H   H e l p f u l   U R L s   ( f o r   t h e   d e v e l o p m e n t ) : %  ) * ) l     �� + ,��   + p j 				"Keycodes:"    http://macbiblioblog.blogspot.com/2014/12/key-codes-for-function-and-special-keys.html    , � - - �   	 	 	 	 " K e y c o d e s : "         h t t p : / / m a c b i b l i o b l o g . b l o g s p o t . c o m / 2 0 1 4 / 1 2 / k e y - c o d e s - f o r - f u n c t i o n - a n d - s p e c i a l - k e y s . h t m l *  . / . l     �� 0 1��   0 v p 				"Inspired by:" https://www.macscripter.net/t/scripting-a-full-path-from-standard-save-as-dialog-box/75178/4    1 � 2 2 �   	 	 	 	 " I n s p i r e d   b y : "   h t t p s : / / w w w . m a c s c r i p t e r . n e t / t / s c r i p t i n g - a - f u l l - p a t h - f r o m - s t a n d a r d - s a v e - a s - d i a l o g - b o x / 7 5 1 7 8 / 4 /  3 4 3 l     ��������  ��  ��   4  5 6 5 l     �� 7 8��   7   Version:		1.0    8 � 9 9    V e r s i o n : 	 	 1 . 0 6  : ; : l     �� < =��   <  				Feb. 2024    = � > >  	 	 	 	 F e b .   2 0 2 4 ;  ? @ ? l     �� A B��   A  				Michael Eichberg    B � C C ( 	 	 	 	 M i c h a e l   E i c h b e r g @  D E D l     ��������  ��  ��   E  F G F l     �� H I��   H   Dependencies:    I � J J    D e p e n d e n c i e s : G  K L K l     �� M N��   M   				Safari 17.3    N � O O     	 	 	 	 S a f a r i   1 7 . 3 L  P Q P i      R S R I     �� T��
�� .aevtoappnull  �   � **** T o      ���� 0 argv  ��   S k    ) U U  V W V l     ��������  ��  ��   W  X Y X Z      Z [���� Z A     \ ] \ n      ^ _ ^ 1    ��
�� 
leng _ o     ���� 0 argv   ] m    ����  [ k     ` `  a b a I   �� c��
�� .ascrcmnt****      � **** c m    	 d d � e e 4 [ e r r o r ]   P a r a m e t e r   m i s s i n g !��   b  f g f I   �� h��
�� .ascrcmnt****      � **** h m     i i � j j h G e n e r a t e P D F s   < N a m e   o f   t h e   H T M L   d o c u m e n t   t o   c o n v e r t . >��   g  k�� k L     l l m    ��������  ��  ��   Y  m n m l   ��������  ��  ��   n  o p o l   �� q r��   q 7 1 split filename into directory and filename parts    r � s s b   s p l i t   f i l e n a m e   i n t o   d i r e c t o r y   a n d   f i l e n a m e   p a r t s p  t u t r    ! v w v n     x y x 4    �� z
�� 
cobj z m    ����  y o    ���� 0 argv   w o      ���� 0 thefile theFile u  { | { r   " * } ~ } c   " (  �  4   " &�� �
�� 
psxf � o   $ %���� 0 thefile theFile � m   & '��
�� 
alis ~ o      ���� 0 theposixfile thePOSIXFile |  � � � O  + 7 � � � r   / 6 � � � c   / 4 � � � n   / 2 � � � 1   0 2��
�� 
pnam � o   / 0���� 0 theposixfile thePOSIXFile � m   2 3��
�� 
ctxt � o      ���� 0 filename   � m   + , � ��                                                                                  MACS  alis    @  Macintosh HD               �Ʀ�BD ����
Finder.app                                                     �����Ʀ�        ����  
 cu             CoreServices  )/:System:Library:CoreServices:Finder.app/    
 F i n d e r . a p p    M a c i n t o s h   H D  &System/Library/CoreServices/Finder.app  / ��   �  � � � r   8 M � � � c   8 K � � � n   8 I � � � 7  9 I�� � �
�� 
cha  � m   = ?����  � l  @ H ����� � \   @ H � � � l  A D ����� � n   A D � � � 1   B D��
�� 
leng � o   A B���� 0 thefile theFile��  ��   � l  D G ����� � n   D G � � � 1   E G��
�� 
leng � o   D E���� 0 filename  ��  ��  ��  ��   � o   8 9���� 0 thefile theFile � m   I J��
�� 
ctxt � o      ���� 0 thepath thePath �  � � � l  N N��������  ��  ��   �  � � � I  N U�� ���
�� .ascrcmnt****      � **** � b   N Q � � � m   N O � � � � �  T a r g e t   p a t h :   � o   O P���� 0 thepath thePath��   �  � � � I  V _�� ���
�� .ascrcmnt****      � **** � b   V [ � � � m   V Y � � � � �  F i l e n a m e :         � o   Y Z���� 0 filename  ��   �  � � � l  ` `��������  ��  ��   �  � � � O  ` l � � � I  f k������
�� .miscactvnull��� ��� null��  ��   � m   ` c � ��                                                                                  sfri  alis    p  Preboot                    �//!BD ����
Safari.app                                                     ������d�        ����  
 cu             Applications  F/:System:Volumes:Preboot:Cryptexes:App:System:Applications:Safari.app/   
 S a f a r i . a p p    P r e b o o t  -/Cryptexes/App/System/Applications/Safari.app   /System/Volumes/Preboot ��   �  � � � O   m { � � � I  s z�� ���
�� .aevtodocnull  �    alis � b   s v � � � o   s t���� 0 thepath thePath � o   t u���� 0 filename  ��   � m   m p � ��                                                                                  sfri  alis    p  Preboot                    �//!BD ����
Safari.app                                                     ������d�        ����  
 cu             Applications  F/:System:Volumes:Preboot:Cryptexes:App:System:Applications:Safari.app/   
 S a f a r i . a p p    P r e b o o t  -/Cryptexes/App/System/Applications/Safari.app   /System/Volumes/Preboot ��   �  � � � l  | � � � � O  | � � � O   � � � � k   � � �  � � � r   � � � � � m   � ���
�� boovtrue � 1   � ���
�� 
pisf �  � � � l  � ���������  ��  ��   �  � � � l  � ��� � ���   �  	 window 1    � � � �    w i n d o w   1 �  � � � l  � � � � � O   � � � � k   � � �  � � � l  � ���������  ��  ��   �  � � � l  � ��� � ���   � ) # PRINT - using keystrokes is faster    � � � � F   P R I N T   -   u s i n g   k e y s t r o k e s   i s   f a s t e r �  � � � I  � ��� � �
�� .prcskprsnull���     ctxt � m   � � � � � � �  p � �� ���
�� 
faal � J   � � � �  ��� � m   � ���
�� eMdsKcmd��  ��   �  � � � l  � ���������  ��  ��   �  � � � l  � ��� � ���   �   check for PRINT sheet    � � � � ,   c h e c k   f o r   P R I N T   s h e e t �  � � � W   � � � � � I  � ��� ���
�� .sysodelanull��� ��� nmbr � m   � � � � ?ə�������   � I  � ��� ���
�� .coredoexnull���     **** � 4   � ��� �
�� 
sheE � m   � ����� ��   �  � � � l  � ���������  ��  ��   �  � � � l  � ��� � ��   �   PRINT sheet     �    P R I N T   s h e e t �  l  � O   � k   �		 

 l  � � I  � �����
�� .prcsperfnull���     actT n   � � 4   � ���
�� 
actT m   � � �  A X S h o w M e n u n   � � 4   � ��
� 
menB m   � ��~�~  n   � � 4   � ��}
�} 
sgrp m   � ��|�|  4   � ��{
�{ 
splg m   � ��z�z ��     pdf menu button    �     p d f   m e n u   b u t t o n  I  � ��y�x
�y .sysodelanull��� ��� nmbr m   � �   ?ə������x   !"! l  � ��w#$�w  # n h set uiElems to entire contents -- uncomment to facilitate debugging in / development with Script Editor   $ �%% �   s e t   u i E l e m s   t o   e n t i r e   c o n t e n t s   - -   u n c o m m e n t   t o   f a c i l i t a t e   d e b u g g i n g   i n   /   d e v e l o p m e n t   w i t h   S c r i p t   E d i t o r" &'& l  �()*( I  ��v+�u
�v .prcsperfnull���     actT+ n   �,-, 4  �t.
�t 
actT. m  // �00  A X P r e s s- n   �121 4  �s3
�s 
menI3 m  44 �55  S a v e   a s   P D F &2 n   �676 4  �r8
�r 
menE8 m  
�q�q 7 n   �9:9 4  �p;
�p 
menB; m  �o�o : n   �<=< 4   ��n>
�n 
sgrp> m   �m�m = 4   � ��l?
�l 
splg? m   � ��k�k �u  )   save as pdf...   * �@@    s a v e   a s   p d f . . .' ABA l �j�i�h�j  �i  �h  B CDC l �g�f�e�g  �f  �e  D EFE l �dGH�d  G $  check for "Save as PDF" sheet   H �II <   c h e c k   f o r   " S a v e   a s   P D F "   s h e e tF JKJ W  :LML I .5�cN�b
�c .sysodelanull��� ��� nmbrN m  .1OO ?ə������b  M I #-�aP�`
�a .coredoexnull���     ****P 4  #)�_Q
�_ 
sheEQ m  '(�^�^ �`  K RSR l ;;�]�\�[�]  �\  �[  S TUT l ;;�ZVW�Z  V   "Save as PDF" sheet   W �XX (   " S a v e   a s   P D F "   s h e e tU YZY l ;	[\][ O  ;	^_^ k  D`` aba r  DMcdc 1  DI�Y
�Y 
ectsd o      �X�X 0 uielems uiElemsb efe l NN�Wgh�W  g "  set the filename of the PDF   h �ii 8   s e t   t h e   f i l e n a m e   o f   t h e   P D Ff jkj r  N^lml b  NSnon o  NO�V�V 0 filename  o m  ORpp �qq  . p d fm n      rsr 1  Y]�U
�U 
valLs 4  SY�Tt
�T 
txtft m  WX�S�S k uvu I _f�Rw�Q
�R .sysodelanull��� ��� nmbrw m  _bxx ?ə������Q  v yzy l gg�P�O�N�P  �O  �N  z {|{ l gg�M}~�M  } H B We have to set the target folder using the "select folder" dialog   ~ � �   W e   h a v e   t o   s e t   t h e   t a r g e t   f o l d e r   u s i n g   t h e   " s e l e c t   f o l d e r "   d i a l o g| ��� l gy���� I gy�L��
�L .prcskcodnull���     ****� m  gj�K�K � �J��I
�J 
faal� J  mu�� ��� m  mp�H
�H eMdsKcmd� ��G� m  ps�F
�F eMdsKsft�G  �I  �   key code 5 is "g"   � ��� $   k e y   c o d e   5   i s   " g "� ��� I z�E��D
�E .sysodelanull��� ��� nmbr� m  z{�C�C �D  � ��� l ���B�A�@�B  �A  �@  � ��� l ������ I ���?��>
�? .prcskcodnull���     ****� m  ���=�= 3�>  � 1 + press "delete" (to make the scrip "safer")   � ��� V   p r e s s   " d e l e t e "   ( t o   m a k e   t h e   s c r i p   " s a f e r " )� ��� I ���<��;
�< .sysodelanull��� ��� nmbr� m  ���� ?��������;  � ��� l ���:�9�8�:  �9  �8  � ��� I ���7��6
�7 .prcskprsnull���     ctxt� o  ���5�5 0 thepath thePath�6  � ��� I ���4��3
�4 .sysodelanull��� ��� nmbr� m  ���� ?��������3  � ��� l ���2�1�0�2  �1  �0  � ��� l ������ I ���/��.
�/ .prcskcodnull���     ****� m  ���-�- $�.  �   press "return"   � ���    p r e s s   " r e t u r n "� ��� I ���,��+
�, .sysodelanull��� ��� nmbr� m  ���� ?�      �+  � ��� l ���*�)�(�*  �)  �(  � ��� l ���'�&�%�'  �&  �%  � ��� I ���$��#
�$ .prcsclicnull��� ��� uiel� l ����"�!� 6 ����� 2  ��� 
�  
butT� = ����� 1  ���
� 
pnam� m  ���� ���  S a v e�"  �!  �#  � ��� l ������  �  �  � ��� l ������  � %  if the file esists, replace it   � ��� >   i f   t h e   f i l e   e s i s t s ,   r e p l a c e   i t� ��� l ������  � / ) check if "Repace Existing" sheet appears   � ��� R   c h e c k   i f   " R e p a c e   E x i s t i n g "   s h e e t   a p p e a r s� ��� l ������  �  �  � ��� Z  ������ I �����
� .coredoexnull���     ****� 4  ����
� 
sheE� m  ���� �  � k  ���� ��� I �����
� .ascrcmnt****      � ****� b  ����� b  ����� m  ���� ���  R e p l a c e d          � o  ���� 0 filename  � m  ���� ���  . p d f�  � ��� O ����� I �����
� .prcsclicnull��� ��� uiel� 4  ����
� 
butT� m  ���� ���  R e p l a c e�  � 4  ���
�
�
 
sheE� m  ���	�	 �  �  � I ����
� .ascrcmnt****      � ****� b  ���� b  ����� m  ���� ���  S a v e d :              � o  ���� 0 filename  � m  ��� ���  . p d f�  � ��� l ����  �  �  �  _ 4  ;A��
� 
sheE� m  ?@� �  \   "Save as PDF" sheet   ] ��� (   " S a v e   a s   P D F "   s h e e tZ ���� l 

��������  ��  ��  ��   4   � ����
�� 
sheE� m   � �����    # PRINT sheet    ���    #   P R I N T   s h e e t ���� l ��������  ��  ��  ��   � 4   � ����
�� 
cwin� m   � �����  �  	 window 1    � ���    w i n d o w   1 � ���� l ��������  ��  ��  ��   � 4   � ����
�� 
pcap� m   � ��� ���  S a f a r i � m   |   �                                                                                  sevs  alis    \  Macintosh HD               �Ʀ�BD ����System Events.app                                              �����Ʀ�        ����  
 cu             CoreServices  0/:System:Library:CoreServices:System Events.app/  $  S y s t e m   E v e n t s . a p p    M a c i n t o s h   H D  -System/Library/CoreServices/System Events.app   / ��   � A ; application "System Events" > application process "Safari"    � � v   a p p l i c a t i o n   " S y s t e m   E v e n t s "   >   a p p l i c a t i o n   p r o c e s s   " S a f a r i " �  l ��������  ��  ��   �� O  ) I (����
�� .coreclosnull���     obj  l $���� n  $	
	 1   $��
�� 
cTab
 4   ��
�� 
cwin m  ���� ��  ��  ��   m  �                                                                                  sfri  alis    p  Preboot                    �//!BD ����
Safari.app                                                     ������d�        ����  
 cu             Applications  F/:System:Volumes:Preboot:Cryptexes:App:System:Applications:Safari.app/   
 S a f a r i . a p p    P r e b o o t  -/Cryptexes/App/System/Applications/Safari.app   /System/Volumes/Preboot ��  ��   Q  l     ��������  ��  ��   �� l     ��������  ��  ��  ��       ����   ��
�� .aevtoappnull  �   � **** �� S������
�� .aevtoappnull  �   � ****�� 0 argv  ��   ���� 0 argv   B�� d�� i���������� ����������� � � ����� ������� ����������� �����������������4/����p������������������������������
�� 
leng
�� .ascrcmnt****      � ****
�� 
cobj�� 0 thefile theFile
�� 
psxf
�� 
alis�� 0 theposixfile thePOSIXFile
�� 
pnam
�� 
ctxt�� 0 filename  
�� 
cha �� 0 thepath thePath
�� .miscactvnull��� ��� null
�� .aevtodocnull  �    alis
�� 
pcap
�� 
pisf
�� 
cwin
�� 
faal
�� eMdsKcmd
�� .prcskprsnull���     ctxt
�� 
sheE
�� .coredoexnull���     ****
�� .sysodelanull��� ��� nmbr
�� 
splg
�� 
sgrp
�� 
menB
�� 
actT
�� .prcsperfnull���     actT
�� 
menE
�� 
menI
�� 
ects�� 0 uielems uiElems
�� 
txtf
�� 
valL�� 
�� eMdsKsft
�� .prcskcodnull���     ****�� 3�� $
�� 
butT  
�� .prcsclicnull��� ��� uiel
�� 
cTab
�� .coreclosnull���     obj ��*��,k �j O�j OiY hO��k/E�O*��/�&E�O� 	��,�&E�UO�[�\[Zk\Z��,��,2�&E�O��%j Oa �%j Oa  *j UOa  	��%j UOa �*a a /�e*a ,FO*a k/ra a a kvl O h*a k/j a j  [OY��O*a k/:*a !k/a "l/a #k/a $a %/j &Oa j  O*a !k/a "l/a #k/a 'k/a (a )/a $a */j &O h*a k/j a j  [OY��O*a k/ �*a +,E` ,O�a -%*a .k/a /,FOa j  Oa 0a a a 1lvl 2Okj  Oa 3j 2Oa 4j  O�j Oa 4j  Oa 5j 2Oa 6j  O*a 7-a 8[�,\Za 981j :O*a k/j  )a ;�%a <%j O*a k/ *a 7a =/j :UY a >�%a ?%j OPUOPUOPUOPUUOa  *a k/a @,j AU ascr  ��ޭ