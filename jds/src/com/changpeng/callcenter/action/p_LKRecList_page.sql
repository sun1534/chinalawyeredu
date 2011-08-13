set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE procedure [dbo].[p_LKRecList_page]
(
	@StartTime 	datetime	,
	@EndTime	datetime	,
	@TelephoneNo	varchar(30)=''	,
	@UserNo		varchar(20)=''	,
    @strLineid varchar(20)='',
    @strPlayAgt varchar(20)='',
    @strProAgt varchar(20)='',
    @callType varchar(20)='',
    @isPro VARCHAR(20)='',
	@page	int,
	@size	int
)
AS
SET 	NOCOUNT 	ON;
select count(*) from baserecord
WHERE	( start>@StartTime AND stop <@EndTime )
	AND( @TelephoneNo = ani OR @TelephoneNo = '' )
	AND( @UserNo = lineagt OR @UserNo ='' )
    AND(@strLineid=lineid or @strLineid='')
    AND(@strPlayAgt=playagt or @strPlayAgt='')
    AND(@strProAgt=proagt or @strProAgt='')
    AND(@callType=type or @callType='')
    AND(@isPro=protag or @isPro='3');


SELECT 	top (@size) * 	FROM BASERECORD
WHERE	( start>@StartTime AND stop <@EndTime )
	AND( @TelephoneNo = ani OR @TelephoneNo = '' )
	AND( @UserNo = lineagt OR @UserNo ='' )
    AND(@strLineid=lineid or @strLineid='')
    AND(@strPlayAgt=playagt or @strPlayAgt='')
    AND(@strProAgt=proagt or @strProAgt='')
    AND(@callType=type or @callType='')
    AND(@isPro=protag or @isPro='3')
	and	id not in
	(
		select top (@size*(@page-1)) id
		from baserecord
		order by id
	)
	order by id