
#cat sources | sed -e s/o\$\(ct\)/odsh/ > s2
#cat s2 | sed -e s/'\\'//

OBJECTS=bins
PATTERN=`c++filt $1`

for i in `cat $OBJECTS`; do
	 nm -AC $i | grep "$PATTERN";
done;
